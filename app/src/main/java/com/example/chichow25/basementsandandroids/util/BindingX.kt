package com.example.chichow25.basementsandandroids.util

import android.databinding.BindingAdapter
import android.support.v7.widget.AppCompatImageView
import com.bumptech.glide.Glide

@BindingAdapter("android:srcCompat")
fun AppCompatImageView.setSrcCompat(src: String) {
    Glide.with(context).load(src).into(this)
}