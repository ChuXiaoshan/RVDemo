package com.cxsplay.rvdemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import com.blankj.utilcode.util.TimeUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by CxS on 2019/2/21
 */

public class GetPicture {
    public static int RESULT_CODE_OK = 0x00913;
    public static int RESULT_CODE_FAILED = 0x00914;
    private static int REQUEST_CODE_ALBUM = 0x00911;
    private static int REQUEST_CODE_CAMERA = 0x00912;
    private final String TAG = GetPicture.class.getSimpleName();

    private GetPictureFragment mFragment;

    public GetPicture(FragmentActivity activity) {
        mFragment = getGetPictureFragment(activity.getSupportFragmentManager());
    }

    public GetPicture(Fragment fragment) {
        mFragment = getGetPictureFragment(fragment.getChildFragmentManager());
    }

    public void openAlbum(GetPictureCallback callback) {
        mFragment.openAlbum(callback);
    }

    public void openCamera(GetPictureCallback callback) {
        mFragment.openCamera(callback);
    }

    private GetPictureFragment getGetPictureFragment(FragmentManager fragmentManager) {
        GetPictureFragment fragment = findGetPictureFragment(fragmentManager);
        if (fragment == null) {
            fragment = new GetPictureFragment();
            fragmentManager.beginTransaction()
                    .add(fragment, TAG)
                    .commitNow();
        }
        return fragment;
    }

    private GetPictureFragment findGetPictureFragment(FragmentManager fragmentManager) {
        return (GetPictureFragment) fragmentManager.findFragmentByTag(TAG);
    }

    public static class GetPictureFragment extends Fragment {

        private GetPictureCallback callback;
        private FragmentActivity activity;
        private String filePath;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
            activity = getActivity();
        }

        public void openAlbum(GetPictureCallback callback) {
            this.callback = callback;
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_ALBUM);
        }

        private String getAppName() {
            try {
                PackageManager pm = activity.getPackageManager();
                PackageInfo pi = pm.getPackageInfo(activity.getApplicationContext().getPackageName(), 0);
                return pi.applicationInfo.loadLabel(pm).toString();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "Pictures";
            }
        }

        public File getFile() {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return null;
            }
            String externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(externalStoragePath, getAppName());
            return !file.exists() && !file.mkdirs() ? null : file;
        }

        public void openCamera(GetPictureCallback callback) {
            this.callback = callback;
            File file = getFile();
            if (null == file) {
                callback.callBack(RESULT_CODE_FAILED, null);
            }
            File imgFile = new File(file, TimeUtils.getNowString(new SimpleDateFormat("yyyyMMddHHmm_ss", Locale.US)) + ".jpg");
            Uri imageUri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imageUri = Uri.fromFile(imgFile);
            } else {
                String authority = activity.getApplicationContext().getPackageName() + ".provider";
                imageUri = FileProvider.getUriForFile(activity, authority, imgFile);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
            filePath = imgFile.getPath();
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (Activity.RESULT_OK != resultCode) {
                callback.callBack(RESULT_CODE_FAILED, null);
                return;
            }
            if (requestCode == REQUEST_CODE_ALBUM) {
                Uri uri = data.getData();
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    assert uri != null;
                    Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        callback.callBack(RESULT_CODE_OK, filePath);
                        cursor.close();
                    }
                }
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                callback.callBack(RESULT_CODE_OK, filePath);
            }
        }
    }

    public interface GetPictureCallback {
        void callBack(int result, String filePath);
    }
}
