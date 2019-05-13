package com.cxsplay.rvdemo.brvah;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.databinding.ActivityBrvahBinding;

import java.util.ArrayList;
import java.util.List;

public class BRVAHActivity extends AppCompatActivity {

    private ActivityBrvahBinding bind;
    List<MultiItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_brvah);
        init();
    }

    private void init() {
        initData();
        MultiItemAdapter adapter = new MultiItemAdapter(list);
        bind.rv.setLayoutManager(new GridLayoutManager(this, 4));
        adapter.setSpanSizeLookup((gridLayoutManager, position) -> list.get(position).getSpanSize());
        bind.rv.setAdapter(adapter);
    }


    private void initData() {
        list = new ArrayList<>();
        list.add(new MultiItem(MultiItem.TYPE_VP, 4, getPageData()));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("淘宝搜", "", R.mipmap.home_icon_taobao)));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("京东搜", "", R.mipmap.home_icon_jingdong)));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("天猫搜", "", R.mipmap.home_icon_tianmao)));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("多多搜", "", R.mipmap.home_icon_pinduoduo)));
        list.add(new MultiItem(MultiItem.TYPE_TRIPLE, 4, getTripleData()));
        list.add(new MultiItem(MultiItem.TYPE_SINGLE_IMG, 4, getTripleData()));
        list.add(new MultiItem(MultiItem.TYPE_SINGLE_PRODUCT, 4, new SimpleData("多多搜", "", R.mipmap.home_icon_pinduoduo)));
        list.add(new MultiItem(MultiItem.TYPE_SINGLE_PRODUCT, 4, new SimpleData("多多搜", "", R.mipmap.home_icon_pinduoduo)));
        list.add(new MultiItem(MultiItem.TYPE_SINGLE_PRODUCT, 4, new SimpleData("多多搜", "", R.mipmap.home_icon_pinduoduo)));
        list.add(new MultiItem(MultiItem.TYPE_SINGLE_PRODUCT, 4, new SimpleData("多多搜", "", R.mipmap.home_icon_pinduoduo)));
        list.add(new MultiItem(MultiItem.TYPE_SINGLE_PRODUCT, 4, new SimpleData("多多搜", "", R.mipmap.home_icon_pinduoduo)));
    }

    private List<SimpleData> getPageData() {
        List<SimpleData> list = new ArrayList<>();
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-729362.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-726443.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-387462.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-718566.jpg"));
        list.add(new SimpleData("", "https://wallpapers.wallhaven.cc/wallpapers/thumb/small/th-727431.jpg"));
        return list;
    }

    private List<SimpleData> getTripleData() {
        List<SimpleData> list = new ArrayList<>();
        list.add(new SimpleData("", "https://img.alicdn.com/simba/img/TB1lOQvxXkoBKNjSZFkSuv4tFXa.jpg"));
        list.add(new SimpleData("", "https://img.alicdn.com/tfs/TB1URS1zMHqK1RjSZFgXXa7JXXa-520-280.jpg_q90_.webp"));
        list.add(new SimpleData("", "https://img.alicdn.com/simba/img/TB19QMcywHqK1RjSZFEwu3GMXXa.png"));
        list.add(new SimpleData("", "https://img.alicdn.com/simba/img/TB19QMcywHqK1RjSZFEwu3GMXXa.png"));
        list.add(new SimpleData("", "https://img.alicdn.com/simba/img/TB19QMcywHqK1RjSZFEwu3GMXXa.png"));
        list.add(new SimpleData("", "https://img.alicdn.com/simba/img/TB19QMcywHqK1RjSZFEwu3GMXXa.png"));
        return list;
    }

}
