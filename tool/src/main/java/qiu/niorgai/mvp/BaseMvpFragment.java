package qiu.niorgai.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import qiu.niorgai.base.BaseFragment;

/**
 * Base
 * Created by jianqiu on 10/26/17.
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mPresenter;

    protected abstract P onCreatePresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter = onCreatePresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
