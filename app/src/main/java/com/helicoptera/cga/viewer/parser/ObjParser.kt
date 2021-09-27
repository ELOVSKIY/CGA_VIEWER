package com.helicoptera.cga.viewer.parser

import com.helicoptera.cga.viewer.parser.exception.ParseException
import com.helicoptera.cga.viewer.parser.parser.NormalVectorParser
import com.helicoptera.cga.viewer.parser.parser.PolygonParser
import com.helicoptera.cga.viewer.parser.parser.TextureVertexParser
import com.helicoptera.cga.viewer.parser.parser.VertexCoordinatesParser
import com.helicoptera.cga.viewer.parser.model.*

class ObjParser {

    private val normalVectorParser = NormalVectorParser()
    private val textureVertexParser = TextureVertexParser()
    private val vertexCoordinatesParser = VertexCoordinatesParser()
    private val polygonParser = PolygonParser()

    fun parseObj(source: String): Obj {
        val normalVectors = mutableListOf<Vector>()
        val polygons = mutableListOf<Polygon>()
        val texturesVertexes = mutableListOf<TextureVertex>()
        val vertexesCoordinates = mutableListOf<VertexCoordinates>()

        try {
            val items = source.split(VALUES_DELIMITER)
            for (item in items) {
                if (item[0] != COMMENT_PREFIX) {
                    val prefixDelimiterIndex = item.indexOf(PREFIX_DELIMITER)
                    val prefix = item.substring(0, prefixDelimiterIndex)
                    val value = item.substring(prefixDelimiterIndex + 1)
                    val processedValue = value.trim()
                    when (prefix) {
                        VERTEX_COORDINATES_PREFIX -> {
                            val vertexCoordinates =
                                vertexCoordinatesParser.parseVertexCoordinates(processedValue)
                            vertexesCoordinates.add(vertexCoordinates)
                        }
                        TEXTURE_VERTEX_PREFIX -> {
                            val textureVertex =
                                textureVertexParser.parseTextureVertex(processedValue)
                            texturesVertexes.add(textureVertex)
                        }
                        NORMAL_VECTOR_PREFIX -> {
                            val normalVector = normalVectorParser.parseNormalVector(processedValue)
                            normalVectors.add(normalVector)
                        }
                        POLYGON_PREFIX -> {
                            val polygonEntity = polygonParser.parsePolygon(processedValue)
                            polygons.add(polygonEntity)
                        }
                        else -> {
                           // throw ParseException("Invalid object prefix: $prefix")
                        }
                    }
                }
            }

            return Obj(vertexesCoordinates, texturesVertexes, normalVectors, polygons)
        } catch (parseException: ParseException) {
            throw parseException
        }
    }

    companion object {
        private const val VERTEX_COORDINATES_PREFIX = "v"
        private const val TEXTURE_VERTEX_PREFIX = "tx"
        private const val NORMAL_VECTOR_PREFIX = "vn"
        private const val POLYGON_PREFIX = "f"
        private const val COMMENT_PREFIX = '#'
        private const val VALUES_DELIMITER = "\n"
        private const val PREFIX_DELIMITER = " "
    }
}