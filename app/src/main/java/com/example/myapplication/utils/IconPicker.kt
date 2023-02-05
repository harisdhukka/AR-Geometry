package com.example.myapplication.utils

import com.example.myapplication.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.app_logo,
        R.drawable.app_logo_,
        R.drawable.app_logo_main
    )
    var currentIcon = 0

    fun getIcon(): Int {
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]
    }
}