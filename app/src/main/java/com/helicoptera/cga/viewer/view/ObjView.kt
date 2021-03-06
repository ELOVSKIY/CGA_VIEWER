package com.helicoptera.cga.viewer.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import com.helicoptera.cga.viewer.parser.model.Obj
import com.helicoptera.cga.viewer.viewer.Renderer
import com.helicoptera.cga.viewer.viewer.Viewer

class ObjView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val renderer = Renderer()
    var viewer: Viewer? = null
        set(value) {
            field = value
            invalidate()
        }

    init {
        setOnTouchListener(TouchListener())
    }

    private val scaleDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                detector?.let { detector ->
                    val scale = detector.scaleFactor.coerceIn(0.2F, 5F)

                    val scaleDiff = if (scale > 1) SCALE_RATIO else -SCALE_RATIO

                    viewer?.let { viewer ->
                        viewer.s += scaleDiff
                        invalidate()
                    }
                }
                return true
            }
        }
        )

    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(width, height, oldw, oldh)

        viewer?.let { viewer ->
            viewer.width = width.toFloat()
            viewer.height = height.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let { canvas ->
            renderer.canvas = canvas
            viewer?.render(renderer)
        }
    }

    private inner class TouchListener : OnTouchListener {
        private var lastTouchX = 0F
        private var lastTouchY = 0F

        override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
            scaleDetector.onTouchEvent(motionEvent)

            if (motionEvent != null) {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastTouchX = motionEvent.x
                        lastTouchY = motionEvent.y
                    }
                    MotionEvent.ACTION_MOVE -> {
                        if (!scaleDetector.isInProgress) {
                            val dX = motionEvent.x - lastTouchX
                            val dY = motionEvent.y - lastTouchY

                            viewer?.let { viewer ->
                                viewer.tx += dX * ROTATION_RATIO
                                viewer.ty += dY * ROTATION_RATIO

                                invalidate()
                            }

                            lastTouchX = motionEvent.x
                            lastTouchY = motionEvent.y
                        }
                    }
                    else -> Unit
                }
            }

            return true
        }
    }

    companion object {
        private const val SCALE_RATIO = 1F
        private const val ROTATION_RATIO = 0.1F
    }
}

