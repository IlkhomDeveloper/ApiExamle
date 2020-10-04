package com.example.firstapi

import android.widget.ImageView
import com.example.education.utils.extensions.dp
import com.squareup.picasso.Picasso

fun ImageView.loadFromUrl(url: String) {
    Picasso.get()
        .load(url)
        .resize(100.dp, 100.dp)
        .centerCrop()
        .into(this)
}