package com.cxsplay.dagger2demo.bean;

import javax.inject.Inject;

/**
 * Created by CxS on 2019/5/20 16:12.
 * Description:
 */
public class Pot {

    private Rose rose;

    @Inject
    public Pot(Rose rose) {
        this.rose = rose;
    }

    public String show() {
        return rose.whisper();
    }
}