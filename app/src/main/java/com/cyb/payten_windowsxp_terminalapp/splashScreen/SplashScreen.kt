package com.cyb.payten_windowsxp_terminalapp.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.splashScreen(
    route: String,
    onUserClick: (String) -> Unit
) = composable(
    route = route
) {
    val splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
    val state = splashScreenViewModel.state.collectAsState()

    SplashScreen(
        state = state.value,
        eventPublisher = {
            splashScreenViewModel.setEvent(it)
        },
        onUserClick = onUserClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    state: SplashScreenContract.SplashScreenUiState,
    eventPublisher: (uiEvent: SplashScreenContract.SplashScreenUiEvent) -> Unit,
    onUserClick: (String) -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top, // Align content at the top
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title Text at the Top
            Text(
                text = "Choose method",
                style = MaterialTheme.typography.headlineMedium.copy( // Further increased text size
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize.times(1.2f), // Enlarged font
                    color = Color(0xFFFFA500),
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 32.dp) // Reduced top padding for closer buttons
            )

            // Buttons Section
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 128.dp), // Bring buttons closer to the title
                verticalArrangement = Arrangement.Top, // Start from the top of remaining space
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                // Enlarged Scan QR Code Button
                Button(
                    onClick = { onUserClick("qr") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(72.dp), // Height kept larger
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(50) // Fully rounded corners
                ) {
                    Text(
                        text = "Scan QR code",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall.copy( // Larger text in the button
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                // OR Separator
                Text(
                    text = "OR",
                    style = MaterialTheme.typography.headlineSmall.copy( // Increased text size
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 16.dp) // Reduce spacing between buttons and text
                )

                Spacer(modifier = Modifier.height(48.dp))


                // Default Size for "Continue without Discount" Button
                Button(
                    onClick = { onUserClick("noQr") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp), // Default size for this button
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(50) // Fully rounded corners
                ) {
                    Text(
                        text = "Continue without Discount",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}