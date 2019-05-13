package com.cxsplay.rvdemo.decoration;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
//        mList.add(new Simple());
    }

    private BaseQuickAdapter.SpanSizeLookup spanSizeLookup = (gridLayoutManager, position) -> {
        if (position < 3) {
            return 1;
        }
        return 3;
    };

    private void init() {
        List1Adapter adapter = new List1Adapter(mList);
        adapter.setSpanSizeLookup(spanSizeLookup);
        bind.rv.setLayoutManager(new GridLayoutManager(this, 3));
        bind.rv.setAdapter(adapter);
        bind.rv.addItemDecoration(new CustomDecoration(ConvertUtils.dp2px(10)));
//        bind.rv.addItemDecoration(new SimpleDividerDecoration(10, Color.BLUE));
        adapter.notifyDataSetChanged();
    }

    public static class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
