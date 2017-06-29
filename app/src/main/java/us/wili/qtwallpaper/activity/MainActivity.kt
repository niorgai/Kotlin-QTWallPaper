package us.wili.qtwallpaper.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.TextView
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.base.BaseActivity
import us.wili.qtwallpaper.fragment.CategoryFragment
import us.wili.qtwallpaper.fragment.HotFragment

class MainActivity: BaseActivity() {

    private var mTextMessage: TextView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mTextMessage!!.setText(R.string.title_home)
                supportFragmentManager.beginTransaction().add(R.id.content, HotFragment.getInstance(), "TAG").commitAllowingStateLoss()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mTextMessage!!.setText(R.string.title_dashboard)
                supportFragmentManager.beginTransaction().add(R.id.content, CategoryFragment.getInstance(), "TAG").commitAllowingStateLoss()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mTextMessage!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextMessage = findViewById(R.id.message) as TextView
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}
