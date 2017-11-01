package qiu.niorgai.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import qiu.niorgai.base.BaseActivity;

/**
 * Base
 * Created by jianqiu on 10/26/17.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    protected abstract P onCreatePresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = onCreatePresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
