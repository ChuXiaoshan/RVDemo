package com.cxsplay.rvdemo.decoration;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.bean.Simple;

import java.util.List;

/**
 * Created by CxS on 2019/4/22 11:37.
 * Description:
 */
public class List1Adapter extends BaseQuickAdapter<Simple, BaseViewHolder> {


    public List1Adapter(@Nullable List<Simple> data) {
        super(R.layout.item_list1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Simple item) {

    }
}
