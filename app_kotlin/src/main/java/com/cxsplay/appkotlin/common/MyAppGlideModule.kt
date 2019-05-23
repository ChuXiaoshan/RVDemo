package com.cxsplay.appkotlin.common

import android.content.Context
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.cxsplay.appkotlin.netk.UnsafeOkHttpClient
import java.io.InputStream

/**
 * Created by CxS on 2019/5/23 16:18.
 * Description:
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context).setMemoryCacheScreens(2f).build()
        builder.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
        val diskCacheSizeBytes = 1024 * 1024 * 100//内部缓存100M
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
    }

    override fun registerComponents(@NonNull context: Context, @NonNull glide: Glide, @NonNull registry: Registry) {
        val client = UnsafeOkHttpClient.unsafeOkHttpClient
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
    }
}
