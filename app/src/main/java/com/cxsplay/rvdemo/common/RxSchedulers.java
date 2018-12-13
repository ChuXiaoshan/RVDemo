package com.cxsplay.rvdemo.common;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CxS on 2017/9/22.
 * RxSchedulers
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> io_main() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
