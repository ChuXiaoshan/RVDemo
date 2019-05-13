package com.cxsplay.rvdemo;

import androidx.recyclerview.widget.DiffUtil;
import com.cxsplay.rvdemo.bean.News;

import java.util.List;

/**
 * Created by CxS on 2018/12/13
 */
public class MyDiffCallback extends DiffUtil.Callback {

    private List<News> mOldDatas, mNewDatas;//看名字

    public MyDiffCallback(List<News> mOldDatas, List<News> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    @Override
    public int getOldListSize() {
        return mOldDatas == null ? 0 : mOldDatas.size();
    }

    @Override
    public int getNewListSize() {
        return mNewDatas == null ? 0 : mNewDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//        return mOldDatas.get(oldItemPosition).getTitle().equals(mNewDatas.get(newItemPosition).getTitle());
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getTitle().equals(mNewDatas.get(newItemPosition).getTitle());
    }
}
