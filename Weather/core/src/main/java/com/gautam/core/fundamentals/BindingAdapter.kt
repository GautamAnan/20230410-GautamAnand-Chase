package com.gautam.core.fundamentals

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.gautam.core.BaseListAdapter
import com.gautam.core.fundamentals.Constants.BASE_URL
import com.gautam.core.utils.ImagesCache

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("image_url")
    fun setImage(view: ImageView, iconName: String?) {
        if (!iconName.isNullOrEmpty()) {
            val image = ImagesCache.getImageFromWarehouse(iconName)
            if (image != null) {
                view.setImageBitmap(image)
            } else {
                Glide.with(view.context).asBitmap()
                    .load("https://openweathermap.org/img/wn/$iconName@2x.png")
                    .apply(
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .centerCrop()
                            .dontTransform()
                    )
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            bitmap: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            view.setImageBitmap(bitmap)
                            ImagesCache.addImageToWarehouse(iconName, bitmap)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {

                        }

                    })
            }
        }
    }

    @JvmStatic
    @BindingAdapter("submitListItems")
    fun submitListItems(recyclerView: RecyclerView?, list: List<Any>?) {
        recyclerView?.adapter?.let { it ->
            list?.let { it1 ->
                (it as ListAdapter<Any, *>).submitList(it1)
            }
        }
    }


    @JvmStatic
    @BindingAdapter("onEditorDoneListener")
    fun onEditorDoneListener(
        editTextCheck: EditText,
        onActionDone: () -> Unit
    ) {
        editTextCheck.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onActionDone.invoke()
            }
            return@OnEditorActionListener true
        })
    }

    @JvmStatic
    @BindingAdapter("onClickListItemListener")
    fun setOnItemClickListener(
        recyclerView: RecyclerView,
        onListItemClickListener: (model: Any, viewId: Int, clickedPosition: Int) -> Unit
    ) {
        (recyclerView.adapter as BaseListAdapter<Any, *>).setOnListItemClickListener(
            onListItemClickListener
        )
    }


}