package qiu.niorgai.tools.statusbar;

import android.app.Activity;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

/**
 * StatusBar Compat
 * Created by jianqiu on 10/23/17.
 */
public class StatusBarCompat {

    //Get alpha color
    private static int calculateStatusBarColor(int color, int alpha) {
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    /**
     * set statusBarColor
     * @param statusColor color
     * @param alpha       0 - 255
     */
    public static void setStatusBarColor(Activity activity, int statusColor, int alpha) {
        setStatusBarColor(activity, calculateStatusBarColor(statusColor, alpha));
    }

    public static void setStatusBarColor(Activity activity, int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarCompatLollipop.setStatusBarColor(activity, statusColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarCompatKitKat.setStatusBarColor(activity, statusColor);
        }
    }

    public static void setStatusBarColorWithCollapsingToolbar(Activity activity, final AppBarLayout appBarLayout, CollapsingToolbarLayout collapsingToolbarLayout,
                                                              Toolbar toolbar, int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarCompatLollipop.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarCompatKitKat.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor);
        }
    }

    //折叠 layout 的 offset
    public static void setStatusBarColorWithCollapsingToolbar(Activity activity, final AppBarLayout appBarLayout, CollapsingToolbarLayout collapsingToolbarLayout,
                                                              Toolbar toolbar, int offset, int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarCompatLollipop.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, offset, statusColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarCompatKitKat.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, offset, statusColor);
        }
    }

    /**
     * change to full screen mode
     * @param hideStatusBarBackground hide status bar alpha Background when SDK > 21, true if hide it
     */
    public static void translucentStatusBar(Activity activity, boolean hideStatusBarBackground) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarCompatLollipop.translucentStatusBar(activity, hideStatusBarBackground);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarCompatKitKat.translucentStatusBar(activity);
        }
    }

    public static void translucentStatusBar(Activity activity) {
        translucentStatusBar(activity, false);
    }
}

