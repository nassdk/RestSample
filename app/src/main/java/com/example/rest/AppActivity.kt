package com.example.rest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.rest.navigation.AppNavHost
import com.example.rest.ui.theme.RestTheme

@ExperimentalAnimationApi
class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestTheme(darkTheme = false) {
                AppNavHost()
            }
        }
    }
}
