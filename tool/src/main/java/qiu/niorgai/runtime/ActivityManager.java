package qiu.niorgai.runtime;

import android.app.Activity;

import java.util.Stack;

/**
 * Custom Activity Stack
 * Created by jianqiu on 10/25/17.
 */
public class ActivityManager {

    private static Stack<Activity> sActivityStack;

    private static volatile ActivityManager mInstance;

    private ActivityManager() {
        sActivityStack = new Stack<>();
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    public static Stack<Activity> getActivityStack() {
        return sActivityStack;
    }

    /**
     * Activity 入栈
     */
    public void pushActivity(Activity activity) {
        sActivityStack.push(activity);
    }

    /**
     * 弹出栈顶 Activity
     */
    public void popActivity() {
        sActivityStack.pop();
    }

    /**
     * 移除某个 Activity
     */
    public void removeActivity(Activity activity) {
        sActivityStack.remove(activity);
    }

    /**
     * 获取栈顶 Activity
     */
    public Activity topActivity() {
        return sActivityStack.lastElement();
    }

    /**
     * 判断栈中是否有指定的 Activity
     */
    public boolean isActivityExists(Class cls) {
        if (sActivityStack != null) {
            for (Activity activity : sActivityStack) {
                if (activity.getClass().equals(cls)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 弹出 Activity 直到栈顶为 cls
     */
    public void popExcept(Class cls) {
        if (cls == null) {
            return;
        }
        while (true) {
            Activity activity = topActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                return;
            }
            activity.finish();
            removeActivity(activity);
        }
    }
}
