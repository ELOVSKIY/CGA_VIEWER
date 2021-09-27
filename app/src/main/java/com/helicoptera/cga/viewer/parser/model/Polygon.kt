package com.helicoptera.cga.viewer.parser.model

data class Polygon(
    val vertexes: List<Vertex>
) : Cloneable {
    public override fun clone(): Polygon {
        return Polygon(vertexes.map { vertex -> vertex.clone() })
    }
}