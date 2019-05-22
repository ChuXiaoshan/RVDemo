package com.cxsplay.appkotlin.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cxsplay.appkotlin.R;
import com.cxsplay.appkotlin.bean.Login;
import com.cxsplay.appkotlin.bean.Product;
import com.cxsplay.appkotlin.databinding.ActivityTestBinding;
import com.cxsplay.appkotlin.entity.Base;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_test);
        Login bean = new Login();
        bind.setBean(bean);



    }

    public interface dd {

        @FormUrlEncoded
        @POST("/ddd")
        Observable<Base> getdd(@Body Product product);
    }
}
