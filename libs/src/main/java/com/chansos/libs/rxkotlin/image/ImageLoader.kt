package com.chansos.libs.rxkotlin.image

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chansos.libs.rxkotlin.AppManager
import com.chansos.libs.rxkotlin.R
import java.io.File

/**
 * 图片加载器
 * 使用Glide项目封装
 * */
@SuppressLint("CheckResult")
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class ImageLoader {
    /**
     * 缓存加载器选项
     * */
    private lateinit var cacheOptions: RequestOptions
    /**
     * 无缓存加载器选项
     * */
    private lateinit var noCacheOptions: RequestOptions

    init {
        resetOptions()
    }

    /**
     * 创建通用加载器选项
     * */
    private fun createDefaultOptions(canCache: Boolean): RequestOptions {
        val options = RequestOptions()
            .placeholder(R.drawable.ic_svg_picture)
            .error(R.drawable.ic_svg_picture_error)
            .optionalCenterInside()
            .override(640, 640)
        if (canCache) {
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        }
        return options
    }

    fun resetOptions() {
        cacheOptions =
                createDefaultOptions(true)
        noCacheOptions =
                createDefaultOptions(false)
    }

    fun setOverrideSize(width: Int, height: Int) {
        cacheOptions.override(width, height)
        noCacheOptions.override(width, height)
    }

    fun setDefaultImage(imageResId: Int) {
        cacheOptions.placeholder(imageResId)
        noCacheOptions.placeholder(imageResId)
    }

    fun setErrorImage(imageResId: Int) {
        cacheOptions.error(imageResId)
        noCacheOptions.error(imageResId)
    }

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param imageUrl 要加载的图片
     * @param activity ImageView所属的Activity实例
     * */
    fun load(imageView: ImageView, imageUrl: String, activity: Activity) =
        loader(imageView, imageUrl, activity, false)

    /**
     * 无缓存加载图片
     *
     * @param imageView ImageView实例
     * @param imageUrl 要加载的图片
     * @param activity ImageView所属的Activity实例
     * */
    fun noCacheLoad(imageView: ImageView, imageUrl: String, activity: Activity) =
        loader(imageView, imageUrl, activity, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param imageUrl 要加载的图片
     * @param fragment ImageView所属的Fragment实例
     * */
    fun load(imageView: ImageView, imageUrl: String, fragment: Fragment) =
        loader(imageView, imageUrl, fragment, false)

    /**
     * 无缓存加载图片
     *
     * @param imageView ImageView实例
     * @param imageUrl 要加载的图片
     * @param fragment ImageView所属的Fragment实例
     * */
    fun noCacheLoad(imageView: ImageView, imageUrl: String, fragment: Fragment) =
        loader(imageView, imageUrl, fragment, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param imageResId 要加载的图片
     * @param activity ImageView所属的Activity实例
     * */
    fun load(imageView: ImageView, imageResId: Int, activity: Activity) =
        loader(imageView, imageResId, activity, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param imageResId 要加载的图片
     * @param fragment ImageView所属的Fragment实例
     * */
    fun load(imageView: ImageView, imageResId: Int, fragment: Fragment) =
        loader(imageView, imageResId, fragment, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param file 要加载的图片
     * @param activity ImageView所属的Activity实例
     * */
    fun load(imageView: ImageView, file: File, activity: Activity) =
        loader(imageView, file, activity, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param file 要加载的图片
     * @param fragment ImageView所属的Fragment实例
     * */
    fun load(imageView: ImageView, file: File, fragment: Fragment) =
        loader(imageView, file, fragment, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param drawable 要加载的图片
     * @param activity ImageView所属的Activity实例
     * */
    fun load(imageView: ImageView, drawable: Drawable, activity: Activity) =
        loader(imageView, drawable, activity, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param drawable 要加载的图片
     * @param fragment ImageView所属的Fragment实例
     * */
    fun load(imageView: ImageView, drawable: Drawable, fragment: Fragment) =
        loader(imageView, drawable, fragment, true)

    /**
     * 加载图片
     *
     * @param imageView ImageView实例
     * @param imageUrl 要加载的图片
     * @param obj ImageView所属的上下文实例
     * @param isNoCache 是否无缓存加载（默认为否）
     * */
    private fun loader(imageView: ImageView, imageUrl: Any, obj: Any, isNoCache: Boolean = false) {
        val glide = when (obj) {
            is Fragment -> Glide.with(obj)
            is Activity -> Glide.with(obj)
            is View -> Glide.with(obj)
            else -> Glide.with(AppManager.last())
        }
        val options: RequestOptions = when (isNoCache) {
            true -> noCacheOptions
            false -> cacheOptions
        }
        val builder = when (imageUrl) {
            is File -> glide.load(imageUrl)
            is Uri -> glide.load(imageUrl)
            is Int -> glide.load(imageUrl)
            is Drawable -> glide.load(imageUrl)
            is Bitmap -> glide.load(imageUrl)
            else -> glide.load(imageUrl as String)
        }
        builder.apply(options).into(imageView)
    }

    /**
     * 释放ImageView中的drawable
     *
     * @param imageView ImageView实例
     * @param activity ImageView所属的Activity实例
     * */
    fun release(imageView: ImageView, activity: Activity) = Glide.with(activity).clear(imageView)

    /**
     * 释放ImageView中的drawable
     *
     * @param imageView ImageView实例
     * @param fragment ImageView所属的Fragment实例
     * */
    fun release(imageView: ImageView, fragment: Fragment) = Glide.with(fragment).clear(imageView)

    /**
     * 清空磁盘中的图片缓存
     * */
    fun clearDiskCache() = Glide.get(AppManager.getContext()).clearDiskCache()

    /**
     * 清空内存中的图片缓存
     * */
    fun clearMemory() = Glide.get(AppManager.getContext()).clearMemory()
}