package us.wili.qtwallpaper.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import qiu.niorgai.runtime.RuntimeContext

/**
 * Application class
 * Created by jianqiu on 5/22/17.
 */
class QtApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        RuntimeContext.onCreate(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        RuntimeContext.onTerminate(this)
    }

}