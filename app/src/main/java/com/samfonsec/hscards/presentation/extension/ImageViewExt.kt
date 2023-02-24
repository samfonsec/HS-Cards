package com.samfonsec.hscards.presentation.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.samfonsec.hscards.R


fun ImageView.loadImage(imageUri: String?) {
    scaleType = ImageView.ScaleType.FIT_XY
    Glide
        .with(context)
        .load(imageUri)
        .placeholder(R.drawable.card_default)
        .into(this)
}