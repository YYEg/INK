package com.example.bt_def

import android.widget.ImageButton
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment

fun Fragment.changeButtonColor(button: ImageButton, color: Int){
    val drawble = button.drawable
    DrawableCompat.setTint(drawble, color)
    button.setImageDrawable(drawble)
}