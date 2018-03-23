package com.example.chichow25.basementsandandroids.graphics.shapes

import android.opengl.GLES20
import com.example.chichow25.basementsandandroids.graphics.GameboardRenderer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Created by testaccount on 3/22/18.
 */
const val COORDS_PER_VERTEX = 3
class Triangle() {
    //important raw strings do not touch
    private val vertexShaderCode: String = """attribute vec4 vPosition;
void main() {
gl_Position = vPosition;
}"""
    private val fragmentShaderCode: String = """precision mediump float;
uniform vec4 vColor;
void main() {
gl_FragColor = vColor;
}"""
    //in counterclockwise order, separated into groups of 3 (top, bot left, bot right)
    private val triangleCoords: FloatArray = floatArrayOf(0.0f, 0.622008459f, 0.0f, -0.5f, -0.311004243f, 0.0f, 0.5f, -0.311004243f, 0.0f)
    //R,G,B,A standard float values
    val color: FloatArray = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)
    private var vertexBuffer: FloatBuffer
    //parse shaders (function is very resource costly, use only once!)
    private val vertexShader = GameboardRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
    private val fragmentShader = GameboardRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
    //create program
    private val program = GLES20.glCreateProgram()
    private val vertexCount = triangleCoords.size/ COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4
    init{
        //(n coordinate values * 4 bytes per float)
        val bb = ByteBuffer.allocateDirect(triangleCoords.size * 4)
        //use native order
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer().apply {
            put(triangleCoords)
            //set buffer to read the first coordinate
            position(0)
        }
        //attach vertex and fragment shaders
        GLES20.glAttachShader(program, vertexShader)
        GLES20.glAttachShader(program, fragmentShader)
        //create program executables
        GLES20.glLinkProgram(program)
    }

    fun draw(){
        GLES20.glUseProgram(program)
        //get handle to vertex shader vPosition manager
        val positionHandle = GLES20.glGetAttribLocation(program, "vPosition")
        //enable handle to triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle)
        //prepare coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexCount)
        //get handle to fragment shader vColor
        val colorHandle = GLES20.glGetUniformLocation(program, "vColor")
        //set color for drawing triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0)
        //draw triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
        //disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle)
    }
}