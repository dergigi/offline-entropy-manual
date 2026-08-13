package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import org.dergigi.offlineentropymanual.data.EntropyPath
import org.dergigi.offlineentropymanual.data.ManualDocument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PathScreen(
    path: EntropyPath,
    onOpenDocument: (ManualDocument) -> Unit,
    onOpenAirgappedDevice: () -> Unit,
    onBack: () -> Unit,
) {
    val sourcesByAuthor = path.documents.groupBy { it.attribution.author }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(path.title) },
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
                        text = path.subtitle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    )
                }
            }
            item {
                Column(modifier = Modifier.padding(bottom = 20.dp)) {
                    if (path.beforeYouBegin.isNotEmpty()) {
                        Text(
                            text = "Before you begin",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        path.beforeYouBegin.forEach { point ->
                            Text(
                                text = "• $point",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 6.dp, start = 4.dp),
                            )
                        }
                    }
                    Text(
                        text = "Take proper precautions",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(
                            top = if (path.beforeYouBegin.isNotEmpty()) 16.dp else 0.dp,
                        ),
                    )
                    path.precautions.forEach { point ->
                        Text(
                            text = "• $point",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 6.dp, start = 4.dp),
                        )
                    }
                }
            }
            items(path.documents, key = { it.id }) { document ->
                DocumentListItem(
                    document = document,
                    onClick = { onOpenDocument(document) },
                )
            }
            item {
                Column(modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)) {
                    Text(
                        text = if (sourcesByAuthor.size == 1) "Source" else "Sources",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    sourcesByAuthor.forEach { (author, documents) ->
                        val attribution = documents.first().attribution
                        Text(
                            text = author,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp),
                        )
                        LinkText(
                            url = attribution.websiteUrl,
                            label = "Open website",
                        )
                        documents.forEach { document ->
                            LinkText(
                                url = document.attribution.documentUrl,
                                label = if (document.attribution.documentUrl.endsWith(".html")) {
                                    "Open source: ${document.title}"
                                } else {
                                    "Open source PDF: ${document.title}"
                                },
                            )
                        }
                    }
                    Text(
                        text = "Original authors retain rights to these documents. " +
                            "This app redistributes them for offline use with attribution.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 12.dp),
                    )
                }
            }
            item {
                SafetyFooter(
                    onOpenAirgappedDevice = onOpenAirgappedDevice,
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                )
            }
        }
    }
}
