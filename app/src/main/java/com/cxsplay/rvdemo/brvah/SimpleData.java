package com.cxsplay.rvdemo.brvah;

/**
 * Created by CxS on 2019/1/3
 */
public class SimpleData {

    private String title;
    private String icon;
    private int iconRes;

    public SimpleData() {
    }

    public SimpleData(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public SimpleData(String title, String icon, int iconRes) {
        this.title = title;
        this.icon = icon;
        this.iconRes = iconRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    @Override
    public String toString() {
        return "SimpleData{" +
                "title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", iconRes=" + iconRes +
                '}';
    }
}
