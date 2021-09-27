package com.helicoptera.cga.viewer.parser.parser

import com.helicoptera.cga.viewer.parser.exception.ParseException
import com.helicoptera.cga.viewer.parser.model.VertexCoordinates

internal class VertexCoordinatesParser {

    internal fun parseVertexCoordinates(source: String) : VertexCoordinates {
        val values = source.split(VALUES_DELIMITER)
        try {
            val x = values[0].toFloat()
            val y = values[1].toFloat()
            val z = values[2].toFloat()
            val w = if (values.size > 3) values[3].toFloat() else W_DEFAULT_VALUE

            return VertexCoordinates(x, y, z, w)
        } catch (e: Exception) {
            throw ParseException("Invalid vertex coordinates value: $source")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = " "
        private const val W_DEFAULT_VALUE = 1.0F
    }
}