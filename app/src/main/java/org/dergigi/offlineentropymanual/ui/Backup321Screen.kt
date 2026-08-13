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

private val RulePoints = listOf(
    "3 copies of your seed backup",
    "on at least 2 different kinds of media (for example paper and steel)",
    "with 1 copy stored in a different physical location",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Backup321Screen(
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("3-2-1 backup rule") },
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
                text = "A simple way to think about durable backups: keep at least",
                style = MaterialTheme.typography.bodyLarge,
            )
            RulePoints.forEach { point ->
                Text(
                    text = "• $point",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 10.dp, start = 4.dp),
                )
            }
            Text(
                text = "Seed backups are not ordinary files. Anyone who finds a copy can " +
                    "steal your bitcoin, so protect each one.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 20.dp),
            )
            Text(
                text = "Advanced users should also consider a BIP39 passphrase and multisig " +
                    "setups. Do your own research.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp),
            )
            AppVersionFooter(modifier = Modifier.padding(top = 32.dp, bottom = 8.dp))
        }
    }
}
