package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

const val nbBall : Int = 1
class CustomView : View, AnkoLogger {

    //private var mPaint = Paint()
    //lateinit var mCircle : MagicCircle
    //lateinit var mCircle2 : MagicCircle
    //lateinit var mAleaCircle : MagicCircle
    lateinit var mAleaTabMagicCircle: TabMagicCircle
    var isTouch : Boolean = false

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context) : this(context, null)

    private fun init() {
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (event.action == MotionEvent.ACTION_DOWN) {
                val touchedCx = event?.rawX?.toInt()
                val touchedCy = event?.rawY?.toInt()
                val mTouchedCircle = MagicCircle(width, height)
                if (touchedCx != null) {
                    if (touchedCy != null) {
                        mTouchedCircle.genererBalleAleatoire(width, height, cx = touchedCx, cy = touchedCy)
                        if (mTouchedCircle.rad + mTouchedCircle.cx > width) {
                            mTouchedCircle.cx = width - (mTouchedCircle.rad * 2)
                            if (mTouchedCircle.cy - mTouchedCircle.rad < 0)
                                mTouchedCircle.cy += mTouchedCircle.rad * 2
                        }
                        if (mTouchedCircle.cx - mTouchedCircle.rad < 0) {
                            mTouchedCircle.cx += mTouchedCircle.rad * 2
                        }
                        if (mTouchedCircle.rad + mTouchedCircle.cy > height) {
                            mTouchedCircle.cy = height - (mTouchedCircle.rad * 2)
                            if (mTouchedCircle.cx - mTouchedCircle.rad < 0) {
                                mTouchedCircle.cx += mTouchedCircle.rad * 2
                            }
                        }
                        if((mTouchedCircle.rad + mTouchedCircle.cx) > width && (mTouchedCircle.rad + mTouchedCircle.cy > height)) {
                            mTouchedCircle.cx = width - (mTouchedCircle.rad * 2)
                            mTouchedCircle.cy = height - (mTouchedCircle.rad * 2)
                        }
                        mAleaTabMagicCircle.tab.add(mTouchedCircle)
                    }
                }
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (ball in mAleaTabMagicCircle.tab) {
            ball.move()
            canvas?.drawCircle(ball.cx, ball.cy, ball.rad, ball.color)
        }
        invalidate()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mAleaTabMagicCircle = TabMagicCircle()
        mAleaTabMagicCircle.genererTabAleatoire(width, height)
    }

    companion object {
        val DELTA = 8
    }
}