package com.mehmetth.itunessearch

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


//android:src => for imageview etc. set Background Image with Glide, Adapter
@BindingAdapter("android:src")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    } else {
        imageView.setImageDrawable(null)
    }
}