package com.cyb.payten_windowsxp_terminalapp.terminalNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cyb.payten_windowsxp_terminalapp.payment.payment
import com.cyb.payten_windowsxp_terminalapp.qrCodeScanner.qrCodeAnalyzer
import com.cyb.payten_windowsxp_terminalapp.splashScreen.splashScreen

@Composable
fun TerminalNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        splashScreen(
            route = "splash_screen",
            onUserClick = {
                if(it.equals("qr"))
                    navController.navigate(route = "qr_code")
                else
                    navController.navigate(route = "payment")
            }
        )

        qrCodeAnalyzer(
            route = "qr_code",
            onClose = {
                navController.navigateUp()
            },
            navigate = {
                navController.navigate(route = "payment")
            }
        )

        payment(
            route = "payment",
            onUserClick = {

            }
        )
    }
}