package com.example.chichow25.basementsandandroids.presenter.graphics

import android.content.Context
import android.opengl.GLSurfaceView

/**
 * Created by testaccount on 3/22/18.
 */
class GameboardGLSurfaceView(context: Context): GLSurfaceView(context) {
    init{
        setEGLContextClientVersion(2)
        val gameboardRenderer = GameboardRenderer()
        setRenderer(gameboardRenderer)
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}