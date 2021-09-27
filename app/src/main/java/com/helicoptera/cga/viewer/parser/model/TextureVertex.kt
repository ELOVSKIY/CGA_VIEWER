package com.helicoptera.cga.viewer.parser.model

data class TextureVertex(
    val u: Float,
    val v: Float = 0.0F,
    val w: Float = 0.0F
) : Cloneable {
    public override fun clone(): TextureVertex {
        return TextureVertex(u, v, w)
    }
}