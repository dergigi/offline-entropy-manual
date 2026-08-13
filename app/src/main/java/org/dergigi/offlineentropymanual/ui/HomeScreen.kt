package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.JoinInner
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
import androidx.compose.ui.unit.dp
import org.dergigi.offlineentropymanual.data.EntropyPath
import org.dergigi.offlineentropymanual.data.EntropyPaths

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenPath: (EntropyPath) -> Unit,
    onOpenAbout: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Offline Entropy Manual") },
                actions = {
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
                Column(modifier = Modifier.padding(bottom = 16.dp, top = 8.dp)) {
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
                        Icon(path.icon, contentDescription = null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenPath(path) },
                )
                HorizontalDivider()
            }
        }
    }
}

private val EntropyPath.icon: ImageVector
    get() = when (id) {
        "dice" -> Icons.Outlined.Casino
        "dice-coin" -> Icons.Outlined.JoinInner
        "coin" -> Icons.Outlined.Toll
        "cards" -> Icons.Outlined.Style
        else -> Icons.Outlined.Info
    }
