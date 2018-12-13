package com.cxsplay.rvdemo.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Created by chuxiaoshan on 2018/8/20.
 * <p>
 * ImageLoader
 */

public class ImageLoader {

    public static int RESULT_OK = 0x003;
    public static int RESULT_FAIL = 0x004;

    private ImageLoader() {
    }

    @BindingAdapter("loadImageById")
    public static void loadImageById(ImageView iv, int resId) {
        GlideApp.with(iv.getContext())
                .load(resId)
                .transition(new DrawableTransitionOptions().crossFade(200))
                .into(iv);
    }

    @BindingAdapter("loadImageByUrl")
    public static void loadImageByUrl(ImageView iv, String url) {
        GlideApp.with(iv.getContext())
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade(200))
                .into(iv);
    }


    public static void loadWithHolder(ImageView iv, String url, int errId, int holderId) {
        GlideApp.with(iv.getContext())
                .load(url)
                .error(errId)
                .placeholder(holderId)
                .transition(new DrawableTransitionOptions().crossFade(200))
                .into(iv);
    }

    @BindingAdapter("loadCircleImage")
    public static void loadCircleImage(ImageView iv, String url) {
        GlideApp.with(iv.getContext())
                .load(url)
                .centerCrop()
                .transform(new MultiTransformation<>(new CircleCrop()))
                .into(iv);
    }

    @BindingAdapter("loadCircleImage")
    public static void loadCircleImage(ImageView iv, int id) {
        GlideApp.with(iv.getContext())
                .load(id)
                .transform(new MultiTransformation<>(new CircleCrop()))
                .into(iv);
    }


    public static void circleWithHolder(ImageView iv, String url, int errId, int holderId) {
        GlideApp.with(iv.getContext())
                .load(url)
                .error(errId)
                .placeholder(holderId)
                .centerCrop()
                .transform(new MultiTransformation<>(new CircleCrop()))
                .into(iv);
    }

    @BindingAdapter("loadCornerImage")
    public static void loadCornerImage(ImageView iv, String url) {
        GlideApp.with(iv.getContext())
                .load(url)
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(10)))
                .into(iv);
    }

    public static void cornerHolder(ImageView iv, String url, int errId, int holderId) {
        GlideApp.with(iv.getContext())
                .load(url)
                .error(errId)
                .placeholder(holderId)
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(10)))
                .into(iv);
    }

    public static void cornerHolderFitCenter(ImageView iv, String url) {
        GlideApp.with(iv.getContext())
                .load(url)
                .fitCenter()
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(10)))
                .into(iv);
    }

    public static void loadCornerImage(ImageView iv, String url, int corner) {
        GlideApp.with(iv.getContext())
                .load(url)
                .centerCrop()
                .transition(new DrawableTransitionOptions().crossFade(200))
                .transform(new RoundedCorners(corner))
                .into(iv);
    }

    public static void loadCornerImage(ImageView iv, Drawable d, int corner) {
        GlideApp.with(iv.getContext())
                .load(d)
                .fitCenter()
                .transition(new DrawableTransitionOptions().crossFade(200))
                .transform(new RoundedCorners(corner))
                .into(iv);
    }

    public static void loadViewBg(final View v, String url) {
        GlideApp.with(v).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                v.setBackground(resource);
            }
        });
    }

    public static void notifyImgInsert(Context cxt, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            final Uri contentUri = Uri.fromFile(file);
            scanIntent.setData(contentUri);
            cxt.sendBroadcast(scanIntent);
        } else {
            final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file));
            cxt.sendBroadcast(intent);
        }
    }


    public static Disposable saveImg(final String url, final Activity activity, final SaveListener listener) {
        return Observable.just(url)
                .compose(RxSchedulers.io_main())
                .map((Function<String, Object>) s -> {
                    GlideApp.with(activity)
                            .asBitmap()
                            .load(url)
                            .listener(new RequestListener<Bitmap>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                    toUIThread(activity, RESULT_FAIL, "", listener);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                    FileOutputStream fos = null;
                                    try {
                                        String folderPath = SDCardUtils.getSDCardPathByEnvironment() + File.separator + "Kuijie" + File.separator + "image";
                                        boolean b = FileUtils.createOrExistsDir(folderPath);
                                        if (b) {
                                            final String filePath = folderPath
                                                    + File.separator
                                                    + TimeUtils.getNowString(new SimpleDateFormat("yyyyMMddHHmmssSSSSSS", Locale.US))
                                                    + ".jpg";
                                            fos = new FileOutputStream(filePath);
                                            resource.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                                            notifyImgInsert(activity, new File(filePath));
                                            fos.close();
                                            toUIThread(activity, RESULT_OK, filePath, listener);
                                        } else {
                                            toUIThread(activity, RESULT_FAIL, "", listener);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        toUIThread(activity, RESULT_FAIL, "", listener);
                                        if (fos != null) {
                                            try {
                                                fos.close();
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                    return false;
                                }
                            })
                            .submit();
                    return 0;
                }).subscribe();
    }

    private static void toUIThread(Activity activity, final int resultCode, final String filePath, final SaveListener listener) {
        activity.runOnUiThread(() -> listener.onResult(resultCode, filePath));
    }

    public interface SaveListener {
        void onResult(int resultCode, String path);
    }
}