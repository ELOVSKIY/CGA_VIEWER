package com.helicoptera.cga.viewer.parser.model

class Obj(
    val vertexesCoordinates: MutableList<VertexCoordinates>,
    val textureVertexes: List<TextureVertex>,
    val normalVectors: List<Vector>,
    val polygons: List<Polygon>
) : Cloneable {

    public override fun clone(): Obj {
        return Obj(
            vertexesCoordinates.map { vertexCoordinates ->  vertexCoordinates.clone()}.toMutableList(),
            textureVertexes.map { textureVertex ->  textureVertex.clone()},
            normalVectors.map { normalVector ->  normalVector.clone()},
            polygons.map { polygon ->  polygon.clone()},
        )
    }
}