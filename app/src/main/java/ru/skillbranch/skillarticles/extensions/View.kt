package ru.skillbranch.skillarticles.extensions

import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

fun View.setMarginOptionally(
    left: Int = marginLeft,
    top: Int = marginTop,
    right: Int = marginRight,
    bottom: Int = marginBottom
) {
    val mlp = this.layoutParams as ViewGroup.MarginLayoutParams
    mlp.let {
        it.leftMargin = left
        it.rightMargin = top
        it.rightMargin = right
        it.bottomMargin = bottom
        this.layoutParams = it
    }
}