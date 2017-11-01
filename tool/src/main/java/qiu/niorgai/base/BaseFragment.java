package qiu.niorgai.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;

import qiu.niorgai.runtime.RuntimeContext;

/**
 * Base Fragment
 * Created by jianqiu on 10/23/17.
 */
public abstract class BaseFragment extends Fragment {

    private static final int RIGHT_TEXT_ID = 123; //右侧文字的id

    //由于Fragment在onDetach后getContext会返回null,需要自己维护Context
    protected AppCompatActivity mContext;

    protected Toolbar mToolbar;

    protected MenuItem mRightTextItem;

    private String rightText = null;

    //避免ViewPager在一开始创建
    private boolean hasLazyLoad = false;

    public void setHasLazyLoad(boolean hasLazyLoad) {
        this.hasLazyLoad = hasLazyLoad;
    }

    public boolean isHasLazyLoad() {
        return hasLazyLoad;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = (AppCompatActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mContext == null && context instanceof AppCompatActivity) {
            this.mContext = (AppCompatActivity) context;
        }
    }

    @Override
    public Context getContext() {
        if (super.getContext() != null) {
            return super.getContext();
        }
        return mContext;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        hasLazyLoad = false;
    }

    /**
     * 懒加载,防止ViewPager重复创建
     */
    protected void onLazyLoad() {
        hasLazyLoad = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isAdded() && !hasLazyLoad) {
            onLazyLoad();
        }
    }

    /**
     * 延迟加载,类似Activity,当第一帧可见时再操作
     */
    private void onDelayLoad() {
        if (getUserVisibleHint() && isAdded() && !hasLazyLoad) {
            onLazyLoad();
        }
    }

    private Handler mDelayHandler;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mDelayHandler = new Handler();
            getActivity().getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    mDelayHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onDelayLoad();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasLazyLoad = false;

        //解决嵌套 Fragment 的bug
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!TextUtils.isEmpty(rightText)) {
            mRightTextItem = menu.add(1, RIGHT_TEXT_ID, 0, rightText);
            mRightTextItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
            case RIGHT_TEXT_ID:
                onRightTextClicked();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initToolBar() {
        if (getView() != null) {
            Context context = RuntimeContext.getApplication();
            int id = context.getResources().getIdentifier("tool_bar", "id", context.getPackageName());
            mToolbar = getView().findViewById(id);
            if (mToolbar == null) {
                return;
            }
            mContext.setSupportActionBar(mToolbar);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isAdded()) {
                        getActivity().finish();
                    }
                }
            });
            ActionBar actionBar = mContext.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setTitle("");
            }
        }
    }

    protected void setHomeAsUpEnable(boolean enable) {
        ActionBar actionBar = mContext.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enable);
        }
    }

    protected void setTitle(int resId) {
        if (mToolbar != null) {
            mToolbar.setTitle(resId);
        }
    }

    protected void setTitle(String str) {
        if (mToolbar != null) {
            mToolbar.setTitle(str);
        }
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        getActivity().invalidateOptionsMenu();
    }

    public void setRightText(int textId) {
        this.rightText = getString(textId);
        getActivity().invalidateOptionsMenu();
    }

    public void setRightItemEnable(boolean enable) {
        if (mRightTextItem != null) {
            mRightTextItem.setEnabled(enable);
        }
    }

    protected void onRightTextClicked() {

    }

    protected View findViewById(int id) {
        return getView() == null ? null : getView().findViewById(id);
    }
}
