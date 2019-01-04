package com.cxsplay.rvdemo.brvah;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.common.ImageLoader;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.List;

/**
 * Created by CxS on 2019/1/3
 */
public class MultiItemAdapter extends BaseMultiItemQuickAdapter<MultiItem, BaseViewHolder> {


    public MultiItemAdapter(List<MultiItem> data) {
        super(data);
        addItemType(MultiItem.TYPE_ACTIO1, R.layout.item_brvah_simple);
        addItemType(MultiItem.TYPE_VP, R.layout.item_brvah_vp);
        addItemType(MultiItem.TYPE_TRIPLE, R.layout.item_brvah_triple);
        addItemType(MultiItem.TYPE_SINGLE_IMG, R.layout.item_brvah_single_img);
        addItemType(MultiItem.TYPE_SINGLE_PRODUCT, R.layout.item_brvah_product);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItem item) {
        switch (helper.getItemViewType()) {
            case MultiItem.TYPE_VP:
                UltraViewPager uvp = helper.getView(R.id.uvp);
                initVp(uvp, item);
                break;
            case MultiItem.TYPE_ACTIO1:
                TextView view = helper.getView(R.id.tv_content);
                SimpleData simpleData = (SimpleData) item.getData();
                view.setText(simpleData.getTitle());
                helper.setImageResource(R.id.iv, simpleData.getIconRes());
                break;
            case MultiItem.TYPE_TRIPLE:
                setTripleData(helper, item);
                break;
            case MultiItem.TYPE_SINGLE_IMG:
                // TODO: 2019/1/4  
                break;
            case MultiItem.TYPE_SINGLE_PRODUCT:
                break;
        }
    }

    private void setTripleData(BaseViewHolder helper, MultiItem item) {
        if (item == null) {
            return;
        }
        List<SimpleData> list = (List<SimpleData>) item.getData();
        if (list == null) {
            return;
        }
        int[] ids = new int[]{R.id.iv_left, R.id.iv_middle, R.id.iv_right, R.id.iv_b_left, R.id.iv_b_middle, R.id.iv_b_right};
        int size = list.size();
        for (int i = 0; i < size; i++) {
            getTripleView(helper, ids[i], list.get(i));
        }
    }

    private void getTripleView(BaseViewHolder helper, int id, SimpleData simpleData) {
        ImageView ivLeft = helper.getView(id);
        ImageLoader.loadImageByUrl(ivLeft, simpleData.getIcon());
    }

    private void initVp(UltraViewPager uvp, MultiItem item) {
        if (item == null) {
            return;
        }
        List<SimpleData> list = (List<SimpleData>) item.getData();
        if (list == null) {
            return;
        }
        UltraPagerAdapter adapter = new UltraPagerAdapter(uvp.getContext(), list);
        uvp.setAdapter(adapter);
        uvp.setOffscreenPageLimit(3);
        uvp.initIndicator();
        uvp.getIndicator()
                .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM)
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.parseColor("#cccccc"))
                .setNormalColor(Color.parseColor("#00ffffff"))
                .setStrokeWidth(ConvertUtils.dp2px(1))
                .setStrokeColor(Color.parseColor("#cccccc"))
                .setIndicatorPadding(ConvertUtils.dp2px(10))
                .setMargin(0, 0, 0, ConvertUtils.dp2px(14))
                .setRadius(ConvertUtils.dp2px(3))
                .build();
        uvp.setInfiniteLoop(true);
        uvp.setAutoScroll(2000);
    }
}
