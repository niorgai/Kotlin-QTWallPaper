package us.wili.qtwallpaper.base

import android.app.Application
import android.content.Context

/**
 * Application class
 * Created by jianqiu on 5/22/17.
 */
class QtApplication: Application() {

    companion object {
        private lateinit var context: Context

        fun getRuntimeContext(): Context {
            return context
        }
    }



    override fun onCreate() {
        super.onCreate()
        context = this
    }
}