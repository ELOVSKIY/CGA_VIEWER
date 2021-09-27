package com.helicoptera.cga.viewer.parser.parser

import com.helicoptera.cga.viewer.parser.model.Polygon
import com.helicoptera.cga.viewer.parser.model.Vertex

internal class PolygonParser {

    private val vertexParser = VertexParser()

    internal fun parsePolygon(source: String): Polygon {
        val vertexes = mutableListOf<Vertex>()

        val values = source.split(VALUES_DELIMITER)
        for (value in values) {
            val vertex = vertexParser.parseVertex(value)
            vertexes.add(vertex)
        }

        return Polygon(vertexes)
    }

    companion object {
        private const val VALUES_DELIMITER = " "
    }
}