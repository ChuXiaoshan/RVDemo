package com.cxsplay.appkotlin.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.cxsplay.appkotlin.R;
import com.cxsplay.appkotlin.bean.Login;
import com.cxsplay.appkotlin.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_test);
        Login bean = new Login();
        bind.setBean(bean);
    }
}
