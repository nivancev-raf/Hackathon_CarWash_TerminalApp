package com.cyb.payten_windowsxp_terminalapp.thanksScreen

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.thankYouScreen(
    route: String,
    onUserClick: () -> Unit
) = composable(
    route = route
) {
    ThankYouScreen(
        route = route,
        onUserClick = onUserClick
    )

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThankYouScreen(route: String, onUserClick: () -> Unit) {
    Scaffold {
        Button(onClick = onUserClick) {
            Text(text = "Thank you for using our service")
        }
    }
}