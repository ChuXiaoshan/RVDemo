package com.cxsplay.rvdemo.matisse;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.blankj.utilcode.util.ScreenUtils;
import com.cxsplay.rvdemo.BuildConfig;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.databinding.ActivityMatisseDemoBinding;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

public class MatisseDemoActivity extends AppCompatActivity {

    private ActivityMatisseDemoBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_matisse_demo);
        init();
    }

    private void init() {
        bind.btn.setOnClickListener(v -> open());
    }

    private void open() {
        Matisse.from(MatisseDemoActivity.this)
                .choose(MimeType.of(MimeType.JPEG), false)
                .countable(false)
//                .maxSelectable(9)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".provider", "Pictures"))//存储到哪里
                .gridExpectedSize(ScreenUtils.getScreenWidth() / 3)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.5f)
                .imageEngine(new MyGlideEngine())
                .forResult(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Log.d("Matisse", "Uris: " + Matisse.obtainResult(data));
            Log.d("Matisse", "Paths: " + Matisse.obtainPathResult(data));
            Log.e("Matisse", "Use the selected photos with original: " + String.valueOf(Matisse.obtainOriginalState(data)));
        }
    }
}
