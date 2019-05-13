package com.cxsplay.rvdemo;

import android.annotation.SuppressLint;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.cxsplay.rvdemo.bean.News;
import com.cxsplay.rvdemo.common.ImageLoader;
import com.cxsplay.rvdemo.databinding.ItemListBinding;

import java.util.List;

/**
 * Created by CxS on 2018/12/12
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    private LayoutInflater inflater;
    private List<News> list;
    private ItemTouchHelper helper;

    public void setList(List<News> list) {
        this.list = list;
    }

    public List<News> getList() {
        return list;
    }

    public NewsAdapter(ItemTouchHelper helper) {
        this.helper = helper;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = inflater.inflate(R.layout.item_list, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int i) {
        News news = list.get(i);
        holder.bind.tvContent.setText(news.getTitle());
        ImageLoader.loadImageByUrl(holder.bind.iv, news.getImg());
        holder.bind.ivDrag.setOnTouchListener((v, event) -> {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    helper.startDrag(holder);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        private ItemListBinding bind;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = DataBindingUtil.bind(itemView);
        }
    }
}
