package com.cyb.payten_windowsxp_terminalapp

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

        val navigateTo = intent.getStringExtra("navigateTo")

        if (navigateTo == "thank_you_screen") {
            // Navigate to the "thank you" screen or perform related actions
            Log.d("MainActivity", "Navigating to: $navigateTo")

            setContent {
                TerminalNavigation("thank_you_screen")
            }
        }else {
            setContent {
                TerminalNavigation("splash_screen")
            }
        }

    }

//    override fun onResume() {
//        super.onResume()
//        setContent {
//            TerminalNavigation("thank_you_screen")
//        }
//    }

}