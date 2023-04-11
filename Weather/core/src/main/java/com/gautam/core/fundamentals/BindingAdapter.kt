package com.gautam.core.fundamentals

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.gautam.core.fundamentals.Constants.BASE_URL

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:image_url")
    fun setImage(view: ImageView, iconName:String?) {
        if (!iconName.isNullOrEmpty()) {
            Glide.with(view.context).load(BASE_URL+"img/wn/$iconName.png")
                .apply( RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .centerCrop()
                    .dontTransform())
                .into(view)
        }
    }

}