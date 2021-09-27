package com.helicoptera.cga.viewer.view

import android.renderscript.Matrix4f
import com.helicoptera.cga.viewer.parser.model.Vector
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

operator fun Matrix4f.times(matrix: Matrix4f): Matrix4f {
    val resultMatrix = Matrix4f(FloatArray(16) { 0F })
    for (i in 0 until 4) {
        for (j in 0 until 4) {
            for (k in 0 until 4) {
                val value = resultMatrix.get(i, j) + this.get(i, k) * matrix[k, j]
                resultMatrix.set(i, j, value)
            }
        }
    }

    return resultMatrix
}

class Matrix {


    companion object {

        fun world(
            s: Float,
            rx: Float,
            ry: Float,
            rz: Float,
            tx: Float,
            ty: Float,
            tz: Float
        ): Matrix4f {
            return scale(s) * (rotateX(rx) * rotateY(ry) * rotateZ(rz)) * translate(tx, ty, tz)
        }

        fun projection(
            FoVRadians: Float,
            aspectRatio: Float,
            zNear: Float,
            zFar: Float
        ): Matrix4f {

            val tanHalfFoV = tan(FoVRadians / 2)

            return Matrix4f(
                floatArrayOf(
                    1 / (aspectRatio * tanHalfFoV), 0F, 0F, 0F,
                    0F, 1 / tanHalfFoV, 0F, 0F,
                    0F, 0F, zFar / (zFar - zNear), zNear * zFar / (zNear - zFar),
                    0F, 0F, -1F, 0F
                )
            ).apply {
                transpose()
            }
        }


        fun viewport(
            width: Float,
            height: Float,
            xMin: Float,
            yMin: Float,
        ): Matrix4f {
            return Matrix4f(
                floatArrayOf(
                    width / 2, 0F, 0F, xMin + width / 2,
                    0F, -height / 2, 0F, yMin + height / 2,
                    0F, 0F, 1F, 0F,
                    0F, 0F, 0F, 1F
                )
            ).apply {
                transpose()
            }
        }

        fun viewer(
            eye: Vector,
            target: Vector,
            up: Vector,
        ): Matrix4f {
            val zAxis = (eye - target).normalize()
            val xAxis = up.cross(zAxis).normalize()
            val yAxis = up

            return Matrix4f(
                floatArrayOf(
                    xAxis.x, xAxis.y, xAxis.z, -(xAxis.scalarMultiplication(eye)),
                    yAxis.x, yAxis.y, yAxis.z, -(yAxis.scalarMultiplication(eye)),
                    zAxis.x, zAxis.y, zAxis.z, -(zAxis.scalarMultiplication(eye)),
                    0F, 0F, 0F, 1F
                )
            ).apply {
                transpose()
            }
        }

        fun scale(x: Float): Matrix4f {
            return Matrix4f(
                floatArrayOf(
                    x, 0F, 0F, 0F,
                    0F, x, 0F, 0F,
                    0F, 0F, x, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun translate(x: Float, y: Float, z: Float): Matrix4f {
            return Matrix4f(
                floatArrayOf(
                    1F, 0F, 0F, x,
                    0F, 1F, 0F, y,
                    0F, 0F, 1F, z,
                    0F, 0F, 0F, 1F
                )
            ).apply {
                transpose()
            }
        }

        fun rotateX(degrees: Float): Matrix4f {
            val angle: Double = Math.PI * degrees / 180.0
            val sin = sin(angle).toFloat()
            val cos = cos(angle).toFloat()

            return Matrix4f(
                floatArrayOf(
                    1F, 0F, 0F, 0F,
                    0F, cos, -sin, 0F,
                    0F, sin, cos, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun rotateY(degrees: Float): Matrix4f {
            val angle: Double = Math.PI * degrees / 180.0
            val sin = sin(angle).toFloat()
            val cos = cos(angle).toFloat()

            return Matrix4f(
                floatArrayOf(
                    cos, 0F, -sin, 0F,
                    0F, 1F, 0F, 0F,
                    sin, 0F, cos, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun rotateZ(degrees: Float): Matrix4f {
            val angle: Double = Math.PI * degrees / 180.0
            val sin = sin(angle).toFloat()
            val cos = cos(angle).toFloat()

            return Matrix4f(
                floatArrayOf(
                    cos, -sin, 0F, 0F,
                    sin, cos, 0F, 0F,
                    0F, 0F, 1F, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }
    }
}