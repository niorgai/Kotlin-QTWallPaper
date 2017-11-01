package qiu.niorgai.runtime;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manage Thread
 * Created by jianqiu on 10/27/17.
 */
public class ThreadManager {

    private static final String TAG = ThreadManager.class.getSimpleName();

    private static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            Log.d(TAG, "exception: " + e);
            if (t != null) {
                Log.d("TAG", t.toString());
            }
            if (e != null) {
                e.printStackTrace();
            }
        }
    }

    private static MyUncaughtExceptionHandler sExceptionHandler = new MyUncaughtExceptionHandler();

    /**
     * The default thread factory.
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            t.setUncaughtExceptionHandler(sExceptionHandler);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    private static DefaultThreadFactory sDefaultThreadFactory = new DefaultThreadFactory();

    private static volatile ThreadManager sInstance;

    public static ThreadManager getInstance() {
        if (sInstance == null) {
            synchronized (ThreadManager.class) {
                if (sInstance == null) {
                    sInstance = new ThreadManager();
                }
            }
        }
        return sInstance;
    }

    private ExecutorService mExecutorService;
    private ScheduledExecutorService mScheduledExecutorService;

    private ThreadManager() {
        mExecutorService = Executors.newCachedThreadPool(sDefaultThreadFactory);
        mScheduledExecutorService = Executors.newScheduledThreadPool(1, sDefaultThreadFactory);
    }

    /**
     * 获取线程池, 如果不需要主动 catch 错误, 请使用 {@link ExecutorService#execute(Runnable)} 方法, 方便 Catch
     */
    public ExecutorService getExecutorService() {
        return mExecutorService;
    }

    /**
     * 获取定时器
     */
    public ScheduledExecutorService getScheduledExecutorService() {
        return mScheduledExecutorService;
    }
}
