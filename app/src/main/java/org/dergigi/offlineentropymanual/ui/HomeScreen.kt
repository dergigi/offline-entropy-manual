package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.JoinInner
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material.icons.outlined.Toll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.dergigi.offlineentropymanual.R
import org.dergigi.offlineentropymanual.data.EntropyPath
import org.dergigi.offlineentropymanual.data.EntropyPaths

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenPath: (EntropyPath) -> Unit,
    onOpenAbout: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenWhatIsEntropy: () -> Unit,
    onOpenAirgappedDevice: () -> Unit,
    onOpenBackup321: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Offline Entropy Manual") },
                actions = {
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Outlined.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = onOpenAbout) {
                        Icon(Icons.Outlined.Info, contentDescription = "About")
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            item {
                Column(modifier = Modifier.padding(bottom = 20.dp, top = 8.dp)) {
                    Text(
                        text = "What is entropy?",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "Unpredictable randomness. A Bitcoin seed needs enough of it " +
                            "that nobody else can guess your words (and thus steal your coins).",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 8.dp),
                    )
                    Text(
                        text = "Computers are deterministic and struggle with true randomness, " +
                            "so gather it offline. That is why this app exists.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 8.dp),
                    )
                    Text(
                        text = "Learn more",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable(onClick = onOpenWhatIsEntropy),
                    )
                }
            }
            item {
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(
                        text = "What do you have at hand?",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "Pick your tools. We'll show the matching offline guides.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
            items(EntropyPaths.all, key = { it.id }) { path ->
                ListItem(
                    headlineContent = { Text(path.title) },
                    supportingContent = { Text(path.subtitle) },
                    leadingContent = {
                        PathLeadingIcon(pathId = path.id)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenPath(path) },
                )
                HorizontalDivider()
            }
            item {
                SafetyFooter(
                    onOpenAirgappedDevice = onOpenAirgappedDevice,
                    onOpenBackup321 = onOpenBackup321,
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                )
            }
        }
    }
}

@Composable
private fun PathLeadingIcon(pathId: String) {
    if (pathId == "d8-d16") {
        Icon(
            painter = painterResource(R.drawable.ic_d8_dice),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    } else {
        Icon(pathIcon(pathId), contentDescription = null)
    }
}

private fun pathIcon(pathId: String): ImageVector = when (pathId) {
    "dice" -> Icons.Outlined.Casino
    "dice-coin" -> Icons.Outlined.JoinInner
    "coin" -> Icons.Outlined.Toll
    "cards" -> Icons.Outlined.Style
    "printer-scissors" -> Icons.Outlined.ContentCut
    else -> Icons.Outlined.Info
}
