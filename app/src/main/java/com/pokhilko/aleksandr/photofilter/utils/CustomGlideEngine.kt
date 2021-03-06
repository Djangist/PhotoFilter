package com.pokhilko.aleksandr.photofilter.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.zhihu.matisse.engine.ImageEngine



/**
 * Created by Aleksandr Pokhilko on 13.12.2018
 */
class CustomGlideEngine : ImageEngine {

    override fun loadThumbnail(context: Context, resize: Int, placeholder: Drawable, imageView: ImageView, uri: Uri) {
        val requestOptions = RequestOptions()
                .override(resize, resize)
                .centerCrop()
                .placeholder(placeholder)
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions)
                .into(imageView)
    }

    override fun loadGifThumbnail(context: Context, resize: Int, placeholder: Drawable, imageView: ImageView,
                                 uri: Uri) {
        val requestOptions = RequestOptions()
                .override(resize, resize)
                .centerCrop()
                .placeholder(placeholder)
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions)
                .into(imageView)
    }

    override fun loadImage(context: Context, resizeX: Int, resizeY: Int, imageView: ImageView, uri: Uri) {
        val requestOptions = RequestOptions()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH)
                .centerCrop()
        Glide.with(context)
                .load(uri)
                .apply(requestOptions)
                .into(imageView)
    }

    override fun loadGifImage(context: Context, resizeX: Int, resizeY: Int, imageView: ImageView, uri: Uri) {
        val requestOptions = RequestOptions()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH)
                .centerCrop()
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(requestOptions)
                .into(imageView)
    }

    override fun supportAnimatedGif(): Boolean {
        return true
    }

}