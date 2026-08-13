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
fun AboutScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About") },
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
                text = "Offline Entropy Manual",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = "A small offline reader for entropy backup guides. " +
                    "Everything ships inside the app, so you can follow the materials without a network.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 12.dp),
            )
            Text(
                text = "Documents",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                text = "BitBox Swiss AG:\n" +
                    "• https://bitbox.swiss/bitbox02/BitBox_Diceware_HowTo.pdf\n" +
                    "• https://bitbox.swiss/bitbox02/BitBox_Diceware_LookupTable.pdf\n\n" +
                    "Jimbojw (Seed Picker Solitaire):\n" +
                    "• https://jimbojw.github.io/seed-picker-solitaire/seed-picker-solitaire.pdf\n\n" +
                    "The Bitcoin Hole:\n" +
                    "• https://thebitcoinhole.com/blog/files/coin-flip-seed-guide.pdf\n" +
                    "• https://thebitcoinhole.com/blog/files/coin-flip-seed-sheet.pdf\n" +
                    "• https://thebitcoinhole.com/blog/files/coin-flip-passphrase-sheet.pdf\n" +
                    "• https://thebitcoinhole.com/blog/files/bip39-wordlist.pdf",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
            )
            Text(
                text = "App code is separate from the bundled documents. " +
                    "Original authors retain rights to their PDFs; this app only redistributes them for offline use with attribution.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 24.dp),
            )
        }
    }
}
