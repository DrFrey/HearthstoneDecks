package com.example.hearthstonedecks.data

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.hearthstonedecks.R

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.broken_img)
            )
            .into(imageView)
    }
}

@BindingAdapter("imageInBackground")
fun loadIntoBackground(layout: RelativeLayout, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(layout.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.broken_img)
            )
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    layout.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    layout.background = placeholder
                }

            })
    }
}

