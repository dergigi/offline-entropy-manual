package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatIsEntropyScreen(
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("What is entropy?") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
        ) {
            Text(
                text = "In cryptography, entropy is unpredictability: outcomes nobody else " +
                    "can guess or reproduce.",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "Computers are deterministic and struggle with true randomness. " +
                    "Because computers can't be trusted it is advisable to generate your " +
                    "own entropy offline, using true physical randomness. That is why " +
                    "this app exists.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp),
            )
            Text(
                text = "A BIP39 seed phrase is just a readable form of that randomness. " +
                    "With enough genuine entropy, the space of possible seeds is so large " +
                    "that guessing yours is not practical.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp),
            )
            Text(
                text = "Dice, coins, cards, and similar physical processes are useful because " +
                    "you can see and control them offline. A website or phone app that " +
                    "\"generates\" a seed for you may also keep a copy.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp),
            )
            Text(
                text = "This app only ships the guides. It does not roll dice for you, and it " +
                    "does not calculate the BIP39 checksum word. Gather the entropy yourself, " +
                    "then finish the checksum on a hardware wallet or other airgapped device " +
                    "you trust.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp),
            )
            AppVersionFooter(modifier = Modifier.padding(top = 32.dp, bottom = 8.dp))
        }
    }
}
