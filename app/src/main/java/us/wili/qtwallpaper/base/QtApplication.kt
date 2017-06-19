package us.wili.qtwallpaper.base

import android.app.Application
import android.content.Context
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Application class
 * Created by jianqiu on 5/22/17.
 */
class QtApplication: Application() {

    companion object {
        private lateinit var context: Context

        private lateinit var executorService : ExecutorService

        fun getRuntimeContext(): Context = context

        fun getExecutors(): ExecutorService = executorService
    }



    override fun onCreate() {
        super.onCreate()
        context = this
        executorService = Executors.newCachedThreadPool()

    }
}