package com.example.chichow25.basementsandandroids.graphics.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Created by testaccount on 3/22/18.
 */
class Square() {
    init{
        val squareCoords = floatArrayOf(-0.5f,  0.5f, 0.0f, //top left
                -0.5f, -0.5f, 0.0f, //bottom left
                0.5f, -0.5f, 0.0f, //bottom right
                0.5f,  0.5f, 0.0f) //top right
        val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3)
        val bb = ByteBuffer.allocateDirect(squareCoords.size * 4)
        bb.order(ByteOrder.nativeOrder())
        val vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(squareCoords)
        vertexBuffer.position(0)
        //(n coordinate values * 2 bytes per short)
        val dlb = ByteBuffer.allocateDirect(drawOrder.size * 2)
        dlb.order(ByteOrder.nativeOrder())
        val drawListBuffer = dlb.asShortBuffer()
        drawListBuffer.put(drawOrder)
        drawListBuffer.position(0)
    }
}