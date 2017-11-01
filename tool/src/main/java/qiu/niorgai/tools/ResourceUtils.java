package qiu.niorgai.tools;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import qiu.niorgai.runtime.RuntimeContext;

/**
 * Resource can use by Application Context
 * Created by jianqiu on 10/23/17.
 */
public class ResourceUtils {

    public static String getString(int resId) {
        return RuntimeContext.getApplication().getString(resId);
    }

    public static float getDimen(int resId) {
        return RuntimeContext.getApplication().getResources().getDimension(resId);
    }

    public static int getColor(int resId) {
        return ContextCompat.getColor(RuntimeContext.getApplication(), resId);
    }

    public static Drawable getDrawable(int resId) {
        Drawable drawable = ContextCompat.getDrawable(RuntimeContext.getApplication(), resId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        return drawable;
    }
}
