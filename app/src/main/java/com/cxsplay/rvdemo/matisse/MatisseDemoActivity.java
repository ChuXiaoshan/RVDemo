package com.cxsplay.rvdemo.matisse;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.cxsplay.rvdemo.BuildConfig;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.common.ImageLoader;
import com.cxsplay.rvdemo.databinding.ActivityMatisseDemoBinding;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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


    private String getAppName() {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getApplicationContext().getPackageName(), 0);
            return pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Pictures";
        }
    }

    private File getFile() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        String externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(externalStoragePath, getAppName());
        return !file.exists() && !file.mkdirs() ? null : file;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Log.d("Matisse", "Uris: " + Matisse.obtainResult(data));
            Log.d("Matisse", "Paths: " + Matisse.obtainPathResult(data));
            Log.e("Matisse", "Use the selected photos with original: " + String.valueOf(Matisse.obtainOriginalState(data)));

            File imgFile = new File(getFile(), TimeUtils.getNowString(new SimpleDateFormat("yyyyMMddHHmm_ss", Locale.US)) + ".jpg");
            try {
                boolean dd = imgFile.createNewFile();
                LogUtils.d("---create--->" + dd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri destinationUri;
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            destinationUri = Uri.fromFile(imgFile);
//            } else {
//                String authority = getApplicationContext().getPackageName() + ".provider";
//                destinationUri = FileProvider.getUriForFile(this, authority, imgFile);
//            }

            LogUtils.d("----->" + destinationUri.getPath());

            UCrop.Options options = new UCrop.Options();
            options.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            options.setActiveWidgetColor(ContextCompat.getColor(this, R.color.colorPrimary));
            options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
//            options.setcolor


            UCrop.of(Matisse.obtainResult(data).get(0), destinationUri)
                    .withAspectRatio(1, 1)
                    .withOptions(options)
//                    .withMaxResultSize(200, 200)
                    .start(this);
        }

        LogUtils.d("1");
        LogUtils.d("---resultCode--->" + resultCode);
        LogUtils.d("---requestCode--->" + requestCode);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            LogUtils.d("2");
            final Uri resultUri = UCrop.getOutput(data);
            String path = resultUri.getPath();
            LogUtils.d("----path--->" + path);
            ImageLoader.loadImageByUrl(bind.iv1, path);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            LogUtils.e("---->" + cropError.getMessage());
        }
    }
}
