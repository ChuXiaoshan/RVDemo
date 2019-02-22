package com.cxsplay.rvdemo.utils;

import android.Manifest;
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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
    public static int OK = 0x00913;
    public static int FAILED = 0x00914;
    public static int DENIED = 0x00915;
    public static int REQUEST_CODE_ALBUM = 0x00911;
    public static int REQUEST_CODE_CAMERA = 0x00912;
    public static int TYPE_CAMERA = 0x00913;
    public static int TYPE_ALBUM = 0x00914;
    private final String TAG = GetPicture.class.getSimpleName();

    private GetPictureFragment mFragment;

    public GetPicture(FragmentActivity activity) {
        mFragment = getMakePhotoFragment(activity.getSupportFragmentManager());
    }

    public GetPicture(Fragment fragment) {
        mFragment = getMakePhotoFragment(fragment.getChildFragmentManager());
    }

    public void openAlbum(GetPictureCallback callback) {
        mFragment.requestStoragePermission(callback);
    }

    public void openCamera(GetPictureCallback callback) {
        mFragment.requestCameraPermission(callback);
    }

    private GetPictureFragment getMakePhotoFragment(FragmentManager fragmentManager) {
        GetPictureFragment fragment = findMakePhotoFragment(fragmentManager);
        if (fragment == null) {
            fragment = new GetPictureFragment();
            fragmentManager.beginTransaction()
                    .add(fragment, TAG)
                    .commitNow();
        }
        return fragment;
    }

    private GetPictureFragment findMakePhotoFragment(FragmentManager fragmentManager) {
        return (GetPictureFragment) fragmentManager.findFragmentByTag(TAG);
    }

    public static class GetPictureFragment extends Fragment {

        private final static int PER_REQUEST_CODE_CAMERA = 0x0067;
        private final static int PER_REQUEST_CODE_STORE = 0x0068;
        private GetPictureCallback callback;
        private FragmentActivity activity;
        private String filePath;
        private int type;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
            activity = getActivity();
        }

        private boolean isAllowed(String permission) {
            int i = ActivityCompat.checkSelfPermission(activity, permission);
            return i == PackageManager.PERMISSION_GRANTED;
        }

        private void requestStoragePermission(GetPictureCallback callback) {
            type = TYPE_ALBUM;
            this.callback = callback;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && !isAllowed(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PER_REQUEST_CODE_STORE);
                return;
            }
            openAlbum();
        }

        private void requestCameraPermission(GetPictureCallback callback) {
            type = TYPE_CAMERA;
            this.callback = callback;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && (!isAllowed(Manifest.permission.CAMERA)
                    || !isAllowed(Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PER_REQUEST_CODE_CAMERA);
                return;
            }
            openCamera();
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    callback.callBack(type, DENIED, null);
                    return;
                }
            }
            if (requestCode == PER_REQUEST_CODE_STORE) {
                openAlbum();
            } else if (requestCode == PER_REQUEST_CODE_CAMERA) {
                openCamera();
            }
        }

        private void openAlbum() {
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

        private File getFile() {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return null;
            }
            String externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(externalStoragePath, getAppName());
            return !file.exists() && !file.mkdirs() ? null : file;
        }

        private void openCamera() {
            File file = getFile();
            if (null == file) {
                callback.callBack(type, FAILED, null);
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
                callback.callBack(type, FAILED, null);
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
                        callback.callBack(type, OK, filePath);
                        cursor.close();
                    }
                }
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                callback.callBack(type, OK, filePath);
            }
        }
    }

    public interface GetPictureCallback {
        void callBack(int type, int result, String filePath);
    }
}
