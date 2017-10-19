package smagabakery.com.bakeryapp.ui.colorlist

import android.content.Context
import android.support.v4.content.ContextCompat
import smagabakery.com.bakeryapp.R

enum class ViewColor {
    RED, BLUE, GREEN, DEFAULT
}

object ColorResources {

    fun resolve(color: ViewColor, context: Context): Int {
        val colorResource = when (color) {
            ViewColor.RED -> R.color.cellColorRed
            ViewColor.BLUE -> R.color.cellColorBlue
            ViewColor.GREEN -> R.color.cellColorGreen
            ViewColor.DEFAULT -> R.color.cellColorDefault
        }
        return ContextCompat.getColor(context, colorResource)
    }
}