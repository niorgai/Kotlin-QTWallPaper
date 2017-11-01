package qiu.niorgai.tools;

import android.content.res.Resources;

import qiu.niorgai.runtime.RuntimeContext;

/**
 * Utils for Screen
 * Created by jianqiu on 10/23/17.
 */
public class ScreenUtils {

    public static int dip2px(float dipValue){
        return Math.round(RuntimeContext.getApplication().getResources().getDisplayMetrics().density * dipValue);
    }

    /**
     * 获取状态栏的高度
     */
    public static int getStatusBarHeight() {
        Resources resources = RuntimeContext.getApplication().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 获取底部导航栏的高度
     */
    public static int getNavigationBarHeight() {
        Resources resources = RuntimeContext.getApplication().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
