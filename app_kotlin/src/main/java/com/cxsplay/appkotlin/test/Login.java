package com.cxsplay.appkotlin.test;

/**
 * Created by CxS on 2019/5/13 17:41.
 * Description:
 */
public class Login {

    private String name;
    private String pwd;
    private int dd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {


        dd = dd == 1 ? 1 : dd--;


        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Login{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
