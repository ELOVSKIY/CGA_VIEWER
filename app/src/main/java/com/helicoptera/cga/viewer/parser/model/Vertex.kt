package com.helicoptera.cga.viewer.parser.model

data class Vertex(
    val vertexCoordinatesIndex: Int,
    val textureVertex: Int? = null,
    val normalVector: Int? = null
) : Cloneable {
    public override fun clone(): Vertex {
        return Vertex(vertexCoordinatesIndex, textureVertex, normalVector)
    }
}