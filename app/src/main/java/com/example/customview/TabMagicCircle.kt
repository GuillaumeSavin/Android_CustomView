package com.example.customview

import com.example.customview.MagicCircle
import com.example.customview.nbBall

class TabMagicCircle {
    var tab : ArrayList<MagicCircle>

    constructor() {
        this.tab = ArrayList<MagicCircle>(nbBall)
    }
    constructor(ptab : ArrayList<MagicCircle>) {
        this.tab = ptab
    }



    fun genererTabAleatoire(width: Int, height: Int) {
        for (i in 0..nbBall) {
            var newBall = MagicCircle(width, height)
            newBall.genererBalleAleatoire(width, height)
            this.tab.add(newBall)
        }
    }
}
