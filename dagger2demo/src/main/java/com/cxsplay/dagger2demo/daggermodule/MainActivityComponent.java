package com.cxsplay.dagger2demo.daggermodule;

import com.cxsplay.dagger2demo.MainActivity;
import dagger.Component;

/**
 * Created by CxS on 2019/5/20 16:15.
 * Description:
 */
@Component
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
