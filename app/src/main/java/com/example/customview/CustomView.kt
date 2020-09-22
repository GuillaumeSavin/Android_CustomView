package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.text.AttributedCharacterIterator

class CustomView : View, AnkoLogger {

    private var mPaint = Paint()
    var magicArray = ArrayList<MagicCircle>()
    lateinit var mCircle : MagicCircle
    lateinit var mCircle2 : MagicCircle

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context) : this(context, null)

    private fun init() {
        mPaint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mCircle.move()
        mCircle2.move()
        canvas?.drawCircle(mCircle.cx, mCircle.cy, mCircle.rad, mPaint)
        canvas?.drawCircle(mCircle2.cx, mCircle2.cy, mCircle2.rad, mPaint)
        invalidate()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        info("width : $width height: $height")
        mCircle = MagicCircle(width, height)
        mCircle2 = MagicCircle(width, height)
        mCircle2.cx = 100F
        mCircle2.cy = 100F
        mCircle2.rad = 80F
    }

    companion object {
        val DELTA = 8
    }
}