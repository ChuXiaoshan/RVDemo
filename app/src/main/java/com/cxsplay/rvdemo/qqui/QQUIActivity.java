package com.cxsplay.rvdemo.qqui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.databinding.ActivityQquiBinding;

public class QQUIActivity extends AppCompatActivity {

    private ActivityQquiBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_qqui);
        bind.qrb.setOnClickListener(v -> startActivity(new Intent(QQUIActivity.this, SectionActivity.class)));
    }
}
