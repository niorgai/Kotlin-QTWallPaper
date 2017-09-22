package us.wili.qtwallpaper.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.base.BaseActivity
import us.wili.qtwallpaper.fragment.CategoryFragment
import us.wili.qtwallpaper.fragment.HotFragment

class MainActivity: BaseActivity() {
    companion object {
        val PAGE_HOT: Int = 0
        val PAGE_CATEGORY: Int = 1
    }

    private lateinit var fragments: Array<Fragment>
    private var currentPage = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changePage(PAGE_HOT)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                changePage(PAGE_CATEGORY)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation : BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fragments = arrayOf(getFragment(savedInstanceState, PAGE_HOT.toString()), getFragment(savedInstanceState, PAGE_CATEGORY.toString()))

    }

    private fun getFragment(bundle: Bundle?, tag: String): Fragment {
        if (bundle != null && supportFragmentManager.getFragment(bundle, tag) != null) {
            return supportFragmentManager.getFragment(bundle, tag)
        }
        if (PAGE_HOT.toString() == (tag)) {
            return HotFragment.getInstance()
        }
        return CategoryFragment.getInstance()
    }

    private fun changePage(page: Int) {
        if (page == currentPage) {
            return
        }
        val fragment = fragments[page]
        val ft = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            ft.add(R.id.content, fragment, page.toString())
        } else{
            ft.show(fragment).hide(fragments[currentPage])
        }
        if (!isFinishing) {
            currentPage = page
            ft.commitAllowingStateLoss()
        }
    }

}
