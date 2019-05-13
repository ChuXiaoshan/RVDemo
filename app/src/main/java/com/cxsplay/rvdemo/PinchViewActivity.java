package com.cxsplay.rvdemo;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.cxsplay.rvdemo.databinding.ActivityPinchBinding;

public class PinchViewActivity extends AppCompatActivity {

    private ActivityPinchBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_pinch);
    }
}
