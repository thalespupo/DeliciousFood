package com.thalespupo.deliciousfood.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}