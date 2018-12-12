package com.cxsplay.rvdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.cxsplay.rvdemo.bean.News;
import com.cxsplay.rvdemo.databinding.ActivityRvBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RVActivity extends AppCompatActivity {

    private ActivityRvBinding bind;
    private NewsAdapter adapter;
    private List<News> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_rv);
        init();
    }

    private void init() {
        list = new ArrayList<>();
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = ItemTouchHelper.LEFT;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                int formPosition = viewHolder.getAdapterPosition();
                int toPosition = viewHolder1.getAdapterPosition();
                Collections.swap(list, formPosition, toPosition);
                adapter.notifyItemMoved(formPosition, toPosition);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                list.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        touchHelper.attachToRecyclerView(bind.rv);
        adapter = new NewsAdapter(touchHelper);
        bind.rv.setLayoutManager(new GridLayoutManager(this, 1));
        bind.rv.setAdapter(adapter);
        getList();
        adapter.setList(list);
        bind.srl.setEnabled(false);
        adapter.notifyDataSetChanged();
    }


    private void getList() {
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
        list.add(new News());
    }
}
