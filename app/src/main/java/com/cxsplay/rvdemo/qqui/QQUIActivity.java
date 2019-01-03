package com.cxsplay.rvdemo.qqui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.databinding.ActivityQquiBinding;

public class QQUIActivity extends AppCompatActivity {

    private ActivityQquiBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_qqui);
    }
}
