package com.cxsplay.rvdemo.bean;

/**
 * Created by CxS on 2018/12/12
 */
public class News {

    private String id;
    private String title;
    private String img;
    private String time;

    public News() {
    }

    public News(String id, String title, String img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public News(String id, String title, String img, String time) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
