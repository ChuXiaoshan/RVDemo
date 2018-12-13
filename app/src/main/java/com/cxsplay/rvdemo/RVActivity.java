package com.cxsplay.rvdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.cxsplay.rvdemo.bean.News;
import com.cxsplay.rvdemo.databinding.ActivityRvBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RVActivity extends AppCompatActivity {

    private ActivityRvBinding bind;
    private LinearLayoutManager lm;
    private NewsAdapter adapter;
    private List<News> list;


    private RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int lastPosition = lm.findLastVisibleItemPosition();
            int itemCount = adapter.getItemCount();
            if ((newState == RecyclerView.SCROLL_STATE_IDLE) && (lastPosition + 1 == itemCount)) {
                addMore();
            }
        }
    };


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
        lm = new LinearLayoutManager(this);
        bind.rv.setLayoutManager(lm);
        bind.rv.setAdapter(adapter);
        bind.rv.addOnScrollListener(sl);
        getList();
        adapter.setList(list);
        bind.srl.setEnabled(false);
        adapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void addMore() {
//        list.add(new News("1", "fheiwonfowefewf", "https://pic2.zhimg.com/v2-fcdf3d785dc0f236f2516ae392a00aaf_b.jpg"));
//        list.add(new News("2", "bniewroewrnefndsifosd", "https://pic3.zhimg.com/v2-06f62b8bb44d3ab2306c3a05ae5e8f32_b.jpg"));
//        list.add(new News("4", "Model Xxxxx", "https://pic1.zhimg.com/v2-d7fe9378b68ed73ca6abf8db9041f68c_b.jpg"));
//        list.add(new News("23", "hfidrfwoe", "ddd"));
        list.add(new News("4", "vbn3fi", ""));
        list.add(new News("4", "vbqwni", ""));
        list.add(new News("4", "vbdsfwni", ""));
        list.add(new News("4", "vbdfni", ""));
        list.add(new News("4", "vbhtrreni", ""));
        list.add(new News("4", "vbjterni", ""));
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(adapter.getList(), list), false);
        adapter.setList(list);
        diffResult.dispatchUpdatesTo(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_add == item.getItemId()) {
            addMore();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getList() {
        list.add(new News("1", "fheiwonfowefewf", "https://pic2.zhimg.com/v2-fcdf3d785dc0f236f2516ae392a00aaf_b.jpg"));
        list.add(new News("4", "Model X", "https://pic1.zhimg.com/v2-d7fe9378b68ed73ca6abf8db9041f68c_b.jpg"));
        list.add(new News("23", "hfidrfwoe", "ddd"));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
        list.add(new News("4", "vbni", ""));
    }
}
