package us.wili.qtwallpaper.base

import android.graphics.Color
import qiu.niorgai.base.BaseActivity

/**
 * BaseActivity
 * Created by jianqiu on 5/19/17.
 */
open class BaseActivity: BaseActivity() {

    override fun isNeedSetStatusBar(): Boolean {
        return true
    }

    override fun getStatusBarColor(): Int {
        return Color.GRAY
    }
}