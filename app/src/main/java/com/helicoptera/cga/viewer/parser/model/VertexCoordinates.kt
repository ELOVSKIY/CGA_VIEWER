package com.helicoptera.cga.viewer.parser.model

import android.renderscript.Matrix4f


data class VertexCoordinates(
    var x: Float,
    var y: Float,
    var z: Float,
    var w: Float
) : Cloneable{
    public override fun clone(): VertexCoordinates {
        return VertexCoordinates(x, y, z, w)
    }

    fun transform(matrix: Matrix4f){
        val dx = (x.toDouble() * matrix.get(0, 0).toDouble() + y.toDouble() * matrix.get(1, 0).toDouble() + z.toDouble() * matrix.get(2, 0).toDouble() + w.toDouble() * matrix.get(3, 0).toDouble()).toFloat()
        val dy = (x.toDouble() * matrix.get(0, 1).toDouble() + y.toDouble() * matrix.get(1, 1).toDouble() + z.toDouble() * matrix.get(2, 1).toDouble() + w.toDouble() * matrix.get(3, 1).toDouble()).toFloat()
        val dz = (x.toDouble() * matrix.get(0, 2).toDouble() + y.toDouble() * matrix.get(1, 2).toDouble() + z.toDouble() * matrix.get(2, 2).toDouble() + w.toDouble() * matrix.get(3, 2).toDouble()).toFloat()
        val dw = (x.toDouble() * matrix.get(0, 3).toDouble() + y.toDouble() * matrix.get(1, 3).toDouble() + z.toDouble() * matrix.get(2, 3).toDouble() + w.toDouble() * matrix.get(3, 3).toDouble()).toFloat()

        x = dx
        y = dy
        z = dz
        w = dw
    }

    operator fun div(value: Float) {
        x /= value
        y /= value
        z /= value
        w /= value
    }

    override fun toString(): String {
        return "$x $y $z $w"
    }
}