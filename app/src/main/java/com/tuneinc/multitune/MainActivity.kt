package com.tuneinc.multitune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.tuneinc.multitune.ui.MultiTuneApp
import com.tuneinc.multitune.ui.theme.MultiTuneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiTuneTheme {
                val navController = rememberNavController()
                MultiTuneApp(navController = navController)
            }
        }
    }
}