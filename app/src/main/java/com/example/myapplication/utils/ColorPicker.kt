package com.example.myapplication.utils

object ColorPicker {
    val colors = arrayOf(
        "#54FB73",
        "#53F8DD",
        "#21436C",
        "#3D3787",
        "#F20035",
        "#2B03B6",
        "#8A38D0",
        "#AA1541"
    )
    var currentColor = 0

    fun getColor(): String {
        currentColor = (currentColor + 1) % colors.size
        return colors[currentColor]
    }
}