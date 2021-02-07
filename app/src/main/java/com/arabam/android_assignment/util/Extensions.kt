package com.arabam.android_assignment.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.arabam.android_assignment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton

fun ImageView.downloadFromUrl(url: String?) {
    this.loadSkeleton()
    val options = RequestOptions().error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
    this.hideSkeleton()
}

@BindingAdapter("android:downloadImage")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url)
}

