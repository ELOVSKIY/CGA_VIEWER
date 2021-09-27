package com.helicoptera.cga.viewer.parser.parser

import com.helicoptera.cga.viewer.parser.exception.ParseException
import com.helicoptera.cga.viewer.parser.model.TextureVertex

internal class TextureVertexParser {

    internal fun parseTextureVertex(source: String) : TextureVertex {
        val values = source.split(VALUES_DELIMITER)
        try {
            val u = values[0].toFloat()
            val v = if (values.size > 1) values[1].toFloat() else V_DEFAULT_VALUE
            val w = if (values.size > 2) values[2].toFloat() else W_DEFAULT_VALUE

            return TextureVertex(u, v, w)
        } catch (e: Exception) {
            throw ParseException("Invalid texture vertex value")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = " "
        private const val V_DEFAULT_VALUE = 0.0F
        private const val W_DEFAULT_VALUE = 0.0F
    }
}