package qiu.niorgai.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hold Application
 * Created by jianqiu on 10/20/17.
 */
public class RuntimeContext implements Application.ActivityLifecycleCallbacks {

    @SuppressLint("StaticFieldLeak")
    private static RuntimeContext instance;

    private ActivityManager mManager;

    private Application mAppContext;

    private AtomicInteger mActivityStartedCount;    //统计 Activity 调用 onStart 周期的次数

    private RuntimeContext(Application application) {
        this.mAppContext = application;
        mManager = ActivityManager.getInstance();
        mActivityStartedCount = new AtomicInteger(0);
        mAppContext.registerActivityLifecycleCallbacks(this);
    }

    private void onTerminate() {
        mAppContext.unregisterActivityLifecycleCallbacks(this);
        mAppContext = null;
    }

    /**
     * This method should be called in {@link Application#onCreate()}
     */
    public static void onCreate(Application application) {
        instance = new RuntimeContext(application);
    }

    /**
     * This method should be called in {@link Application#onTerminate()}
     */
    public static void onTerminate(Application application) {
        instance.onTerminate();
        instance = null;
    }

    public static Application getApplication() {
        return instance.mAppContext;
    }

    public ActivityManager getManager() {
        return mManager;
    }

    public Application getAppContext() {
        return mAppContext;
    }

    /**
     * App 是否可见
     * @return  true 为是
     */
    public boolean isAppRuningForeGround() {
        return mActivityStartedCount.get() == 0;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mManager.pushActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        mActivityStartedCount.addAndGet(1);
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mActivityStartedCount.decrementAndGet();
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mManager.removeActivity(activity);
    }
}