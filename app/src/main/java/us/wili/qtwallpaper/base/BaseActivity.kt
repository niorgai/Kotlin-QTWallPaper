package us.wili.qtwallpaper.base

import qiu.niorgai.base.BaseActivity

/**
 * BaseActivity
 * Created by jianqiu on 5/19/17.
 */
open class BaseActivity: BaseActivity() {

    override fun isNeedSetStatusBar(): Boolean {
        return true
    }
}