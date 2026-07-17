package com.example.slidingpuzzleai.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slidingpuzzleai.SlidingPuzzleViewModel
import kotlin.math.max
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.slidingpuzzleai.ui.theme.OrangeText
import androidx.compose.material3.TopAppBarDefaults
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SlidingPuzzleScreen(viewModel: SlidingPuzzleViewModel) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlidingPuzzleContent(
    tiles: List<Int>,
    moveCount: Int,
    isSolved: Boolean,
    imageResource: DrawableResource,
    verticalBias: Float,
    horizontalBias: Float,
    onTileClick: (Int) -> Unit,
    onShuffle: () -> Unit,
    onNewGame: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Sammy's Schiebepuzzle 🐈") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Reference Image Section
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Vorschau:",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = MaterialTheme.colorScheme.secondary // Lighter orange
                )
                ReferenceImage(imageResource)
            }

            Text(
                text = "Züge: $moveCount",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary // Lighter orange
            )

            Box(
                modifier = Modifier
                    .sizeIn(maxWidth = 380.dp)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                PuzzleGrid(
                    tiles = tiles,
                    imageResource = imageResource,
                    verticalBias = verticalBias,
                    horizontalBias = horizontalBias,
                    onTileClick = onTileClick
                )
            }

            if (isSolved && (moveCount > 0)) {
                Text(
                    text = "Gewonnen! 🎉",
                    color = Color(0xFF4CAF50),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            // Shuffle Button
            OutlinedButton(
                onClick = onShuffle,
                modifier = Modifier.fillMaxWidth(0.25f),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
                    brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.secondary)
                )
            ) {
                Text("Mischen", fontWeight = FontWeight.Bold, color = Color.White)
            }

            // New Game Button
            OutlinedButton(
                onClick = onNewGame,
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(0.25f),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
                    brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.secondary)
                )
            ) {
                Text("Nächstes Bild", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun PuzzlePreview() {
    // Note: This would normally need dummy data for imageResource and verticalBias
}

@Composable
fun ReferenceImage(imageResource: DrawableResource) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .border(2.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = "Reference",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun PuzzleGrid(
    tiles: List<Int>,
    imageResource: DrawableResource,
    verticalBias: Float,
    horizontalBias: Float,
    onTileClick: (Int) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        val gridSize = 3
        val tileSize = this.maxWidth / gridSize

        tiles.forEachIndexed { index, tileValue ->
            if (tileValue != 8) { // 8 is the empty slot
                val row = index / gridSize
                val col = index % gridSize

                Box(
                    modifier = Modifier
                        .size(tileSize)
                        .offset(x = tileSize * col, y = tileSize * row)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                        .clickable { onTileClick(index) }
                ) {
                    TileContent(tileValue, imageResource, verticalBias, horizontalBias)
                }
            }
        }
    }
}

@Composable
fun TileContent(tileValue: Int, imageResource: DrawableResource, verticalBias: Float, horizontalBias: Float) {
    val row = tileValue / 3
    val col = tileValue % 3
    
    val painter = painterResource(imageResource)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        
        val intrinsicSize = painter.intrinsicSize
        if (intrinsicSize != Size.Unspecified && intrinsicSize.width > 0 && intrinsicSize.height > 0) {
            // Determine scale to fill 3x3 grid (Crop logic)
            val targetW = w * 3
            val targetH = h * 3
            
            val scale = max(targetW / intrinsicSize.width, targetH / intrinsicSize.height)
            val scaledW = intrinsicSize.width * scale
            val scaledH = intrinsicSize.height * scale
            
            // Offset calculation using biases
            // 0.0f = Top/Left, 0.5f = Center, 1.0f = Bottom/Right
            val centerX = (targetW - scaledW) * horizontalBias
            val centerY = (targetH - scaledH) * verticalBias
            
            // Final tile offset
            val offsetX = -col * w + centerX
            val offsetY = -row * h + centerY
            
            withTransform({
                translate(left = offsetX, top = offsetY)
            }) {
                with(painter) {
                    draw(size = Size(scaledW, scaledH))
                }
            }
        } else {
            // Fallback while loading size
            withTransform({
                translate(left = -col * w, top = -row * h)
            }) {
                with(painter) {
                    draw(size = Size(w * 3, h * 3))
                }
            }
        }
    }
}
