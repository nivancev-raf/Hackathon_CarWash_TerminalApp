package com.cyb.payten_windowsxp_terminalapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cyb.payten_windowsxp_terminalapp.terminalNavigation.TerminalNavigation
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TerminalNavigation("splash_screen")
        }
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val navigateTo = intent?.getStringExtra("navigateTo")
        Log.d("MainActivityyyy", "Navigating to: $navigateTo")
        if (navigateTo == "washing") {
            setContent {
                TerminalNavigation("washing")
            }
        }
    }
}