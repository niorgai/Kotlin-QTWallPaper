package qiu.niorgai.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import qiu.niorgai.tools.statusbar.StatusBarCompat;
import qiu.niorgai.runtime.RuntimeContext;

/**
 * Base Activity
 * Created by jianqiu on 10/23/17.
 */
public class BaseActivity extends AppCompatActivity {

    private static final int RIGHT_TEXT_ID = 123; //右侧文字的id

    private Handler mDelayHandler = null;

    protected Toolbar mToolbar;

    protected MenuItem mRightTextItem;

    private String rightText = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews(savedInstanceState);

        initData(savedInstanceState);

        mDelayHandler = new Handler();
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mDelayHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onDelayLoad();
                        mDelayHandler = null;
                    }
                });
            }
        });
    }

    protected void initToolBar() {
        Context context = RuntimeContext.getApplication();
        int id = context.getResources().getIdentifier("tool_bar", "id", context.getPackageName());
        mToolbar = findViewById(id);
        if (mToolbar == null) {
            return;
        }
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }

    protected void initViews(@Nullable Bundle savedInstanceState) {

    }

    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    protected void onDelayLoad() {

    }

    protected void onRightTextClicked() {

    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        invalidateOptionsMenu();
    }

    public void setRightText(int textId) {
        this.rightText = getString(textId);
        invalidateOptionsMenu();
    }

    public void setRightItemEnable(boolean enable) {
        if (mRightTextItem != null) {
            mRightTextItem.setEnabled(enable);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (!TextUtils.isEmpty(rightText)) {
            mRightTextItem = menu.add(1, RIGHT_TEXT_ID, 0, rightText);
            mRightTextItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case RIGHT_TEXT_ID:
                onRightTextClicked();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (mToolbar != null) {
            mToolbar.setTitle(titleId);
        }
    }

    public void setIcon(int resId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setIcon(resId);
        }
    }

    public void setIcon(Drawable drawable) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setIcon(drawable);
        }
    }

    //是否需要设置状态栏颜色
    protected boolean isNeedSetStatusBar() {
        return true;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (isNeedSetStatusBar()) {
            StatusBarCompat.setStatusBarColor(this);
        }
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        if (isNeedSetStatusBar()) {
            StatusBarCompat.setStatusBarColor(this);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        if (isNeedSetStatusBar()) {
            StatusBarCompat.setStatusBarColor(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }
}
