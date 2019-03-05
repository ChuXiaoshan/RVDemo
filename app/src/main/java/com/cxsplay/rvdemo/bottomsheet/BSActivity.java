package com.cxsplay.rvdemo.bottomsheet;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.TextView;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.cxsplay.rvdemo.R;
import com.yinglan.scrolllayout.ScrollLayout;

public class BSActivity extends AppCompatActivity {

    private ScrollLayout mScrollLayout;
    private TextView text_foot;

    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if (currentProgress >= 0) {
                float precent = 255 * currentProgress;
                if (precent > 255) {
                    precent = 255;
                } else if (precent < 0) {
                    precent = 0;
                }
//                mScrollLayout.getBackground().setAlpha(255 - (int) precent);
            }
//            if (text_foot.getVisibility() == View.VISIBLE)
//                text_foot.setVisibility(View.GONE);
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
                text_foot.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onChildScroll(int top) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bs);
        initView();
    }

    public boolean isNavigationBarShow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(this).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }


    private int ddd() {
        Context context = getApplicationContext();
        DisplayMetrics localDisplayMetrics = context.getResources().getDisplayMetrics();
        return localDisplayMetrics.heightPixels;
    }


    private void initView() {


        LogUtils.d("---isSupportNavBar--->" + BarUtils.isSupportNavBar());
        LogUtils.d("---isNavBarVisible--->" + BarUtils.isNavBarVisible(this));
        LogUtils.d("---getActionBarHeight--->" + BarUtils.getActionBarHeight());
        LogUtils.d("---getStatusBarHeight--->" + BarUtils.getStatusBarHeight());
        LogUtils.d("---getNavBarHeight--->" + BarUtils.getNavBarHeight());
        LogUtils.d("---getScreenHeight--->" + ScreenUtils.getScreenHeight());
        LogUtils.d("---getDisplayHeight--->" + ddd());


//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root);
        mScrollLayout = (ScrollLayout) findViewById(R.id.scroll_down_layout);
        text_foot = (TextView) findViewById(R.id.text_foot);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_view);
//        recyclerView.setAdapter(new RecyclerViewAdapter(this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

        /**设置 setting*/
        mScrollLayout.setMinOffset(0);
        mScrollLayout.setMaxOffset((int) (ScreenUtils.getScreenHeight() * 0.5));
//        mScrollLayout.setExitOffset(BarUtils.getNavBarHeight() + ConvertUtils.dp2px(50));
        mScrollLayout.setExitOffset(BarUtils.getNavBarHeight() + ConvertUtils.dp2px(115));
//        BarUtils.setNavBarVisibility(this, false);
//        ToastUtils.showShort("---isSupportNavBar--->" + isNavigationBarShow());

        mScrollLayout.setIsSupportExit(true);
        mScrollLayout.isSupportExit();
        mScrollLayout.setAllowHorizontalScroll(true);
//        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
        mScrollLayout.setToExit();

        text_foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollLayout.setToOpen();
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollLayout.setToOpen();
            }
        });
    }
}
