package com.bna.game.view.logic

/**
 * Created by per6 on 5/17/18.
 */
enum class TokenItem {
    ARCHER {
        val offset = 3f
        override fun create(gameManager: GameManager, x: Float, y: Float) {
//            val board = gameManager.board
    }
};
    abstract fun create(gameManager: GameManager, x: Float, y: Float)
}