package com.helicoptera.cga.viewer.viewer

import com.helicoptera.cga.viewer.parser.model.Obj
import com.helicoptera.cga.viewer.parser.model.Vector
import com.helicoptera.cga.viewer.view.Matrix
import com.helicoptera.cga.viewer.view.times
import kotlin.math.PI

class Viewer(private val obj: Obj) {

    var width = 775f
    var height = 409f

    var s = 5F
    var rx = 0F
    var ry = 0F
    var rz = 0F
    var tx = 0F
    var ty = 0F
    var tz = 0F

    private val target = Vector(0F, 0F, 0F)
    private var up = Vector(0F, 1F, 0F)
    private var eye = Vector(0F, 4F, -20F)

    fun render(renderer: Renderer) {
        val model = obj.clone()
        model.apply {

            val world = Matrix.world(s, rx, ry, rz, tx, ty, tz);

            val viewer = Matrix.viewer(eye, target, up);

            val projection = Matrix.projection(FOV_IN_RADIANS, width / height, Z_NEAR, Z_FAR);

            val viewport = Matrix.viewport(width, height, X_MIN, Y_MIN);

            val transformation = world * viewer * projection

            vertexesCoordinates.forEach { vertexCoordinates ->
                vertexCoordinates.transform(transformation)
                vertexCoordinates.div(vertexCoordinates.w)
                vertexCoordinates.transform(viewport)
            }

            for (polygon in polygons) {
                for (i in 0 until polygon.vertexes.size) {
                    val x1 = vertexesCoordinates[polygon.vertexes[i].vertexCoordinatesIndex - 1].x
                    val y1 = vertexesCoordinates[polygon.vertexes[i].vertexCoordinatesIndex - 1].y

                    val nextIndex = if (i == polygon.vertexes.size - 1) i - 1 else i

                    val x2 = vertexesCoordinates[polygon.vertexes[nextIndex + 1].vertexCoordinatesIndex - 1].x
                    val y2 = vertexesCoordinates[polygon.vertexes[nextIndex + 1].vertexCoordinatesIndex - 1].y

                    renderer.drawLine(x1, y1, x2, y2)
                }
            }
        }
    }

    companion object {
        private const val Z_NEAR = 50F
        private const val Z_FAR = 100F
        private const val X_MIN = 0F
        private const val Y_MIN = 0F
        private const val FOV_IN_RADIANS = (PI / 4).toFloat()
    }
}