package com.example.slidingpuzzleai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.jetbrains.compose.resources.DrawableResource
import slidingpuzzleai.app.generated.resources.*
import kotlin.math.abs

class SlidingPuzzleViewModel : ViewModel() {

    data class PuzzleImage(
        val resource: DrawableResource,
        val verticalBias: Float = 0.5f,
        val horizontalBias: Float = 0.5f
    )

    var tiles by mutableStateOf((0..8).toList())
        private set

    var moveCount by mutableStateOf(0)
        private set

    var isSolved by mutableStateOf(true)
        private set

    private val curatedImages = listOf(
        PuzzleImage(
            Res.drawable.own_images_sammy_1, 
            verticalBias = 0.35f, // Reveal more bottom than 0.2, but keep eyes
            horizontalBias = 0.7f // Shift right
        ),
        PuzzleImage(Res.drawable.own_images_sammy_2),
        PuzzleImage(Res.drawable.own_images_sammy_3)
    )

    private var currentImageIndex = 0

    var currentImage by mutableStateOf(curatedImages[0])
        private set

    private val size = 3

    fun onTileClick(index: Int) {
        val emptyIndex = tiles.indexOf(8)
        if (isAdjacent(index, emptyIndex)) {
            val newTiles = tiles.toMutableList()
            newTiles[emptyIndex] = tiles[index]
            newTiles[index] = 8
            tiles = newTiles
            moveCount++
            checkWin()
        }
    }

    private fun isAdjacent(idx1: Int, idx2: Int): Boolean {
        val r1 = idx1 / size
        val c1 = idx1 % size
        val r2 = idx2 / size
        val c2 = idx2 % size
        return (abs(r1 - r2) == 1 && c1 == c2) || (abs(c1 - c2) == 1 && r1 == r2)
    }

    fun shuffle() {
        val newTiles = (0..8).toMutableList()
        tiles = newTiles
        moveCount = 0
        isSolved = false

        // Start shuffling from solved state to ensure solvability
        var currentEmptyIndex = 8
        repeat(100) {
            val neighbors = getNeighbors(currentEmptyIndex)
            val randomNeighbor = neighbors.random()
            
            val temp = newTiles[currentEmptyIndex]
            newTiles[currentEmptyIndex] = newTiles[randomNeighbor]
            newTiles[randomNeighbor] = temp
            
            currentEmptyIndex = randomNeighbor
        }
        tiles = newTiles
    }

    private fun getNeighbors(index: Int): List<Int> {
        val r = index / size
        val c = index % size
        val neighbors = mutableListOf<Int>()
        if (r > 0) neighbors.add((r - 1) * size + c)
        if (r < size - 1) neighbors.add((r + 1) * size + c)
        if (c > 0) neighbors.add(r * size + (c - 1))
        if (c < size - 1) neighbors.add(r * size + (c + 1))
        return neighbors
    }

    fun resetCurrent() {
        shuffle()
    }

    fun startNewGame() {
        currentImageIndex = (currentImageIndex + 1) % curatedImages.size
        currentImage = curatedImages[currentImageIndex]
        tiles = (0..8).toList()
        moveCount = 0
        isSolved = true
    }

    private fun checkWin() {
        isSolved = tiles == (0..8).toList()
    }
}
