package com.cyb.payten_windowsxp_terminalapp

import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cyb.payten_windowsxp_terminalapp.terminalNavigation.TerminalNavigation
import com.cyb.payten_windowsxp_terminalapp.ui.theme.Payten_WindowsXP_TerminalAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TerminalNavigation()
        }

    }

}