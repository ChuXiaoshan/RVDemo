package com.cxsplay.rvdemo.bean;

/**
 * Created by CxS on 2019/4/22 11:32.
 * Description:
 */
public class Simple {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
