package com.helicoptera.cga.viewer.viewer

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Renderer() {

    private val linePaint = Paint().apply {
        color = Color.parseColor("#4A148C")
        strokeWidth = 5F
    }

    var canvas: Canvas? = null

    fun drawLine(startX: Float, startY: Float, stopX: Float, stopY: Float) {
        canvas?.drawLine(startX, startY, stopX, stopY, linePaint)
    }
}