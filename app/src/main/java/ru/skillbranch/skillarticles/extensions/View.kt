package ru.skillbranch.skillarticles.extensions

import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import androidx.navigation.NavDestination
import com.google.android.material.bottomnavigation.BottomNavigationView

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

fun View.setPaddingOptionally(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

fun BottomNavigationView.selectDestination(destination: NavDestination) {
    menu.forEach {
        if (it.itemId == destination.id) it.isChecked = true
    }
}

fun BottomNavigationView.selectItem(itemId: Int?) {
    itemId ?: return
    for (item in menu.iterator()) {
        if (item.itemId == itemId) {
            item.isChecked = true
            break
        }
    }
}