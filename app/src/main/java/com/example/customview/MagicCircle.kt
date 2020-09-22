package com.example.customview

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

data class MagicCircle(val maxX: Int, val maxY: Int) : AnkoLogger
{
    var cx: Float = 50F
    var cy: Float = 50F
    var rad: Float = 40F
    var dx = CustomView.DELTA
    var dy = CustomView.DELTA

    fun move() {
        info("condition x : $cx $rad ${maxX.toFloat()} $dx")
        info("condition y :$cy $rad ${maxY.toFloat()} $dy")

        when {
            cx!in rad..(maxX.toFloat()-rad) && cy!in rad..(maxY.toFloat()-rad) -> {
                dy = -dy
                dx = -dx }
            cx!in rad..(maxX.toFloat()-rad) -> dx = -dx
            cy!in rad..(maxY.toFloat()-rad) -> dy = -dy
        }
        cx += dx
        cy += dy
    }
}