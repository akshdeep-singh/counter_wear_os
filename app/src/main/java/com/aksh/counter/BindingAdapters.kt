package com.aksh.counter

import android.util.TypedValue
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:textSize")
fun TextView.setTextSize(textSize: Int) {
    setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
}