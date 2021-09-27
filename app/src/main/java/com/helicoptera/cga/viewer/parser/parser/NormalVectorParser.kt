package com.helicoptera.cga.viewer.parser.parser

import com.helicoptera.cga.viewer.parser.exception.ParseException
import com.helicoptera.cga.viewer.parser.model.Vector

internal class NormalVectorParser {

    internal fun parseNormalVector(source: String) : Vector {
        val values = source.split(VALUES_DELIMITER)
        try {
            val i = values[0].toFloat()
            val j = values[1].toFloat()
            val k = values[2].toFloat()

            return Vector(i, j, k)
        } catch (e: Exception) {
            throw ParseException("Invalid normal vector value: $source")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = " "
    }
}