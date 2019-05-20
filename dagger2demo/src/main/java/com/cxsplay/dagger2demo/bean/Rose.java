package com.cxsplay.dagger2demo.bean;

import javax.inject.Inject;

/**
 * Created by CxS on 2019/5/20 16:12.
 * Description:
 */
public class Rose {

    @Inject
    public Rose() {}

    public String whisper()  {
        return "热恋";
    }
}
