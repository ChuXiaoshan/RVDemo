package com.cxsplay.rvdemo.decoration;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.bean.Simple;
import com.cxsplay.rvdemo.databinding.ActivityList1Binding;

import java.util.ArrayList;
import java.util.List;

public class List1Activity extends AppCompatActivity {

    private ActivityList1Binding bind;
    private List<Simple> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_list1);
        initData();
        init();
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
        mList.add(new Simple());
    }

    private void init() {
        List1Adapter adapter = new List1Adapter(mList);
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
        bind.rv.setAdapter(adapter);
        bind.rv.addItemDecoration(new CustomDecoration(this, R.drawable.recyclerview_item_divider));
//        bind.rv.addItemDecoration(new SimpleDividerDecoration(10, Color.BLUE));
        adapter.notifyDataSetChanged();
    }
}
