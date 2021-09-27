package com.helicoptera.cga.viewer.parser.model

import kotlin.math.pow
import kotlin.math.sqrt

data class Vector(
    val x: Float,
    val y: Float,
    val z: Float,
) : Cloneable {
    public override fun clone(): Vector {
        return Vector(x, y, z)
    }

    operator fun minus(vector: Vector): Vector {
        return Vector(x - vector.x, y - vector.y, z - vector.z)
    }

    fun normalize(): Vector {
        val length = sqrt(x.pow(2) + y.pow(2) + z.pow(2))

        return Vector(x / length, y / length, z / length)
    }

    fun cross(vector: Vector): Vector {
        return Vector(
            y * vector.z - z * vector.y,
            z * vector.x - x * vector.z,
            x * vector.y - y * vector.x
        )
    }

    fun scalarMultiplication(vector: Vector): Float {
        return x * vector.x + y * vector.y + z * vector.z
    }
}
