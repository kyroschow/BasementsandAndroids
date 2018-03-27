package com.example.chichow25.basementsandandroids.presenter.graphics

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.example.chichow25.basementsandandroids.presenter.graphics.shapes.Square
import com.example.chichow25.basementsandandroids.presenter.graphics.shapes.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by testaccount on 3/22/18.
 */
class GameboardRenderer: GLSurfaceView.Renderer {
    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        val triangle = Triangle()
        val square = Square()
    }
    companion object {
        fun loadShader(type: Int, shaderCode: String): Int{
            val shader = GLES20.glCreateShader(type)
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)

            return shader
        }
    }
}