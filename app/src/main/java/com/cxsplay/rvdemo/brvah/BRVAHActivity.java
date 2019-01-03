package com.cxsplay.rvdemo.brvah;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
        list.add(new MultiItem(MultiItem.TYPE_VP, 4, new SimpleData("hello1", "")));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("hello1", "")));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("hello2", "")));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("hello3", "")));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("hello4", "")));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 2, new SimpleData("hello5", "")));
        list.add(new MultiItem(MultiItem.TYPE_ACTIO1, 1, new SimpleData("hello6", "")));

    }

}
