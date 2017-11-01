package qiu.niorgai.mvp;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Base
 * Created by jianqiu on 10/26/17.
 */
public class BasePresenter<V> {

    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    public void onDestroy() {
        mView = null;
    }

    /**
     * 回到 UI 线程通知 View
     * @return 如果父类已经处理, 返回 true, 否则返回 false 交给子类处理
     */
    public boolean runOnUiThread(final Runnable task) {
        if (mView == null || task == null) {
            return false;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mView != null) {
                    task.run();
                }
            }
        };
        if (mView instanceof Activity) {
            ((Activity) mView).runOnUiThread(runnable);
            return true;
        } else if (mView instanceof android.support.v4.app.Fragment) {
            if (((Fragment) mView).isAdded()) {
                ((Fragment) mView).getActivity().runOnUiThread(runnable);
            }
            return true;
        } else if (mView instanceof android.app.Fragment) {
            if (((android.app.Fragment) mView).isAdded()) {
                ((android.app.Fragment) mView).getActivity().runOnUiThread(runnable);
            }
            return true;
        } else if (mView instanceof View) {
            ((View) mView).post(runnable);
            return true;
        }
        return false;
    }
}
