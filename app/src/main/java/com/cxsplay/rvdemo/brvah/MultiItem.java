package com.cxsplay.rvdemo.brvah;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by CxS on 2019/1/3
 */
public class MultiItem implements MultiItemEntity {

    public final static int TYPE_VP = 0;
    public final static int TYPE_ACTIO1 = 1;
    public final static int TYPE_SINGLE_IMG = 2;
    public final static int TYPE_SINGLE_PRODUCT = 3;
    public final static int TYPE_TRIPLE = 4;
    public final static int TYPE_QUARTER_PRODUCT = 5;

    private int type;

    private int spanSize;

    private Object Data;

    public MultiItem() {
    }

    public MultiItem(int type, int spanSize, Object data) {
        this.type = type;
        this.spanSize = spanSize;
        Data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "MultiItem{" +
                "type=" + type +
                ", spanSize=" + spanSize +
                ", Data=" + Data +
                '}';
    }

    @Override
    public int getItemType() {
        return type;
    }
}
