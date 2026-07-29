package com.example.slidingpuzzleai

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.unit.dp
import com.example.slidingpuzzleai.SlidingPuzzleViewModel
import com.example.slidingpuzzleai.ui.SlidingPuzzleContent
import com.example.slidingpuzzleai.ui.theme.SlidingPuzzleAiTheme
import androidx.compose.runtime.remember

fun main() = application {
    val windowState = rememberWindowState(width = 600.dp, height = 850.dp)
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "Sammy's Schiebepuzzle 🐈",
        state = windowState
    ) {
        SlidingPuzzleAiTheme {
            val viewModel = remember { SlidingPuzzleViewModel() }
            
            SlidingPuzzleContent(
                tiles = viewModel.tiles,
                moveCount = viewModel.moveCount,
                isSolved = viewModel.isSolved,
                imageResource = viewModel.currentImage.resource,
                verticalBias = viewModel.currentImage.verticalBias,
                horizontalBias = viewModel.currentImage.horizontalBias,
                onTileClick = viewModel::onTileClick,
                onShuffle = viewModel::shuffle,
                onNewGame = viewModel::startNewGame
            )
        }
    }
}
