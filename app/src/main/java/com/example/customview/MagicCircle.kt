package com.example.customview

import android.graphics.Color
import android.graphics.Color.rgb
import android.graphics.Paint
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

data class MagicCircle(val maxX: Int, val maxY: Int) : AnkoLogger
{
    var cx: Float = 50F
    var cy: Float = 50F
    var rad: Float = 40F
    var color : Paint = Paint()
    var dx = CustomView.DELTA
    var dy = CustomView.DELTA

    fun move() {
        when {
            cx!in rad..(maxX.toFloat()-rad) && cy!in rad..(maxY.toFloat()-rad) -> {
                dy = -dy
                dx = -dx
            }
            cx!in rad..(maxX.toFloat()-rad) -> dx = -dx
            cy!in rad..(maxY.toFloat()-rad) -> dy = -dy
        }
        cx += dx
        cy += dy
    }

    fun genererBalleAleatoire(width: Int, height: Int, cx: Int = -1, cy: Int = -1) {
        val aleaColor = Paint()
        var aleaCx = -1F
        var aleaCy = -1F
        val aleaRed = ThreadLocalRandom.current().nextInt(0, 255)
        val aleaBlue = ThreadLocalRandom.current().nextInt(0, 255)
        val aleaGreen = ThreadLocalRandom.current().nextInt(0, 255)
        val aleaRad = ThreadLocalRandom.current().nextInt(1, maxY / 10).toFloat()
        val maxX = width - aleaRad
        val maxY = height - aleaRad
        if (cx == -1 && cy == -1) {
            aleaCx = ThreadLocalRandom.current().nextInt(aleaRad.toInt(), maxX.toInt()).toFloat()
            aleaCy = ThreadLocalRandom.current().nextInt(aleaRad.toInt(), maxY.toInt()).toFloat()
        } else {
            aleaCx = cx.toFloat()
            aleaCy = cy.toFloat()
        }

        val aleaDx = ThreadLocalRandom.current().nextInt(1, maxX.toInt() / 100)
        val aleaDy = ThreadLocalRandom.current().nextInt(1, maxY.toInt() / 100)
        val aleaBalle = MagicCircle(width, height)
        aleaColor.color = rgb(aleaRed, aleaGreen, aleaBlue)
        val calcX = cx + rad
        val calcY = cy + rad

        this.rad = aleaRad
        this.cx = aleaCx
        this.cy = aleaCy
        this.dx = aleaDx
        this.dy = aleaDy
        this.color = aleaColor
    }
}