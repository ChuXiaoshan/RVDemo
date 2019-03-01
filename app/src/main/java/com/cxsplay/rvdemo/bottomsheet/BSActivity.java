package com.cxsplay.rvdemo.bottomsheet;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.databinding.ActivityBsBinding;

public class BSActivity extends AppCompatActivity {

    private ActivityBsBinding bind;
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_bs);
        init();
    }

    private void init() {
        behavior = BottomSheetBehavior.from(bind.bottom);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
                String state = "null";
                switch (newState) {
                    case 1:
                        state = "STATE_DRAGGING";//过渡状态此时用户正在向上或者向下拖动bottom sheet
                        break;
                    case 2:
                        state = "STATE_SETTLING"; // 视图从脱离手指自由滑动到最终停下的这一小段时间
                        break;
                    case 3:
                        state = "STATE_EXPANDED"; //处于完全展开的状态

                        break;
                    case 4:
                        state = "STATE_COLLAPSED"; //默认的折叠状态
                        break;
                    case 5:
                        state = "STATE_HIDDEN"; //下滑动完全隐藏 bottom sheet
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                Log.d("BottomSheetDemo", "slideOffset:" + slideOffset);
            }
        });


//        /**
//         * bottom sheet state change listener
//         * we are changing button text when sheet changed state
//         * */
//        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        ToastUtils.showShort("STATE_HIDDEN");
//                        break;
//                    case BottomSheetBehavior.STATE_EXPANDED:
//                        ToastUtils.showShort("STATE_EXPANDED");
//                        break;
//                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        ToastUtils.showShort("STATE_COLLAPSED");
//                        break;
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        ToastUtils.showShort("STATE_DRAGGING");
//
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        ToastUtils.showShort("STATE_SETTLING");
//                        break;
//                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
//                        ToastUtils.showShort("STATE_HALF_EXPANDED");
//                        break;
//                    case BottomSheetBehavior.PEEK_HEIGHT_AUTO:
//                        ToastUtils.showShort("PEEK_HEIGHT_AUTO");
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });
    }
}
