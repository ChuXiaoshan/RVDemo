package com.cxsplay.rvdemo.brvah;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.common.ImageLoader;
import com.cxsplay.rvdemo.databinding.ItemVpBinding;

import java.util.List;

/**
 * Created by CxS on 2019/1/3
 */
public class UltraPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private List<SimpleData> data;

    public UltraPagerAdapter(Context cxt, List<SimpleData> data) {
        this.data = data;
        layoutInflater = LayoutInflater.from(cxt);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemVpBinding vpBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_vp, null, false);
        SimpleData simpleData = data.get(position);
        ImageLoader.loadImageByUrl(vpBinding.iv, simpleData.getIcon());
        container.addView(vpBinding.getRoot());
        return vpBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
