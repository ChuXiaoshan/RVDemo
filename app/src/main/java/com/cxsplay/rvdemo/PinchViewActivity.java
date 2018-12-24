package com.cxsplay.rvdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.cxsplay.rvdemo.databinding.ActivityPinchBinding;

public class PinchViewActivity extends AppCompatActivity {

    private ActivityPinchBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_pinch);
    }
}
