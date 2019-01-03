package com.cxsplay.rvdemo.brvah;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.TextView;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxsplay.rvdemo.R;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CxS on 2019/1/3
 */
public class MultiItemAdapter extends BaseMultiItemQuickAdapter<MultiItem, BaseViewHolder> {


    public MultiItemAdapter(List<MultiItem> data) {
        super(data);
        addItemType(MultiItem.TYPE_ACTIO1, R.layout.item_brvah_simple);
        addItemType(MultiItem.TYPE_VP, R.layout.item_brvah_vp);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItem item) {

        switch (helper.getItemViewType()) {
            case MultiItem.TYPE_VP:
                UltraViewPager uvp = helper.getView(R.id.uvp);
                initVp(uvp);
                LogUtils.d("---------->convert");
                break;
            case MultiItem.TYPE_ACTIO1:
                TextView view = helper.getView(R.id.tv_content);
                SimpleData simpleData = (SimpleData) item.getData();
                view.setText(simpleData.getTitle());
                break;
        }
    }

    private void initVp(UltraViewPager uvp) {
        List<SimpleData> list = new ArrayList<>();
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-729362.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-726443.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-387462.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-718566.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-727431.jpg"));

        UltraPagerAdapter adapter = new UltraPagerAdapter(uvp.getContext(), list);
        uvp.setAdapter(adapter);
        uvp.setOffscreenPageLimit(3);
        uvp.initIndicator();
        uvp.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
//                .setNormalColor(ContextCompat.getColor(uvp.getContext(), R.color.colorAccent))
//                .setFocusColor(ContextCompat.getColor(uvp.getContext(), R.color.colorPrimary))
                .set((BitmapDrawable) ContextCompat.getDrawable(uvp.getContext(), R.mipmap.ic_launcher))
                .setFocusResId(R.drawable.bg_white_oval)
                .setRadius(ConvertUtils.dp2px(6));
        uvp.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        uvp.getIndicator().build();

//
//        //initialize built-in indicator
//        uvp.initIndicator();
////set style of indicators
//        uvp.getIndicator()
//                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
//                .setFocusColor(Color.GREEN)
//                .setNormalColor(Color.WHITE)
//                .setRadius(10);
////set the alignment
//        uvp.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
////construct built-in indicator, and add it to  UltraViewPager
//        uvp.getIndicator().build();


        uvp.setInfiniteLoop(true);
        uvp.setAutoScroll(2000);
    }
}
