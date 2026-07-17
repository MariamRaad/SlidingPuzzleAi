package com.example.slidingpuzzleai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.slidingpuzzleai.ui.SlidingPuzzleScreen
import com.example.slidingpuzzleai.ui.theme.SlidingPuzzleAiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlidingPuzzleAiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val viewModel = viewModel<SlidingPuzzleViewModel>()
                    SlidingPuzzleScreen(viewModel)
                }
            }
        }
    }
}
