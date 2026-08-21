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
import org.dergigi.offlineentropymanual.data.ManualDocuments

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBack: () -> Unit,
    onOpenAirgappedDevice: () -> Unit,
    onOpenBackup321: () -> Unit,
) {
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
                text = "The last BIP39 word (12th or 24th) is a checksum. This app does not " +
                    "calculate it. Finish that step on a hardware wallet or another airgapped " +
                    "device you trust.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 12.dp),
            )
            Text(
                text = "Inspired by ligi's Survival Manual: an offline-first reader with a " +
                    "simple path through serious material.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 12.dp),
            )
            LinkText(
                url = "https://github.com/ligi/SurvivalManual",
                label = "github.com/ligi/SurvivalManual",
            )
            Text(
                text = "Attribution",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                text = "The guides below are not written by this app. " +
                    "They are bundled for offline use with credit to their authors. " +
                    "Tap a link to open it in your browser when you have a network.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
            )
            ManualDocuments.groupedByAuthor().forEach { (_, documents) ->
                val attribution = documents.first().attribution
                Text(
                    text = attribution.author,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 20.dp),
                )
                LinkText(
                    url = attribution.websiteUrl,
                    label = "Open website",
                )
                LicenseLink(license = attribution.license)
                documents.forEach { document ->
                    Text(
                        text = document.title,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 10.dp),
                    )
                    LinkText(
                        url = document.attribution.documentUrl,
                        label = if (document.attribution.documentUrl.endsWith(".html")) {
                            "Open source"
                        } else {
                            "Open source PDF"
                        },
                    )
                }
            }
            Text(
                text = "App code is MIT. Bundled PDFs remain under their authors' rights.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                text = "Feedback",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                text = "Found a problem? Open a GitHub issue when you have a network.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
            )
            LinkText(
                url = "https://github.com/dergigi/offline-entropy-manual/issues/new",
                label = "Report an issue",
            )
            SafetyFooter(
                onOpenAirgappedDevice = onOpenAirgappedDevice,
                onOpenBackup321 = onOpenBackup321,
                modifier = Modifier.padding(top = 32.dp, bottom = 8.dp),
            )
        }
    }
}
