package com.cxsplay.rvdemo.photo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.common.ImageLoader;
import com.cxsplay.rvdemo.databinding.ActivityPhotoBinding;
import com.cxsplay.rvdemo.utils.GetPicture;

public class PhotoActivity extends AppCompatActivity {

    private ActivityPhotoBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        init();
    }

    private void init() {
        bind.btnOpenAlbum.setOnClickListener(v ->
                new GetPicture(PhotoActivity.this)
                        .openAlbum((type, result, filePath) -> {
                            if (result == GetPicture.OK) {
                                ImageLoader.loadImageByUrl(bind.iv, filePath);
                            } else if (result == GetPicture.DENIED) {
                                ToastUtils.showShort("授权被拒绝");
                            } else {
                                ToastUtils.showShort("获取图片失败");
                            }
                        }));
        bind.btnOpenCamera.setOnClickListener(v ->
                new GetPicture(PhotoActivity.this)
                        .openCamera((type, result, filePath) -> {
                            if (result == GetPicture.OK) {
                                ImageLoader.loadImageByUrl(bind.iv, filePath);
                            } else if (result == GetPicture.DENIED) {
                                ToastUtils.showShort("授权被拒绝");
                            } else {
                                ToastUtils.showShort("获取图片失败");
                            }
                        }));

        bind.btnOpenCamera.post(() -> LogUtils.d("------"));
    }
}
