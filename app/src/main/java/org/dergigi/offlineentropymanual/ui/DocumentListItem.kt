package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.dergigi.offlineentropymanual.data.ManualDocument

@Composable
fun DocumentListItem(
    document: ManualDocument,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = { Text(document.title) },
        supportingContent = {
            Text("${document.subtitle}\n${document.attribution.author}")
        },
        leadingContent = {
            Icon(Icons.AutoMirrored.Outlined.MenuBook, contentDescription = null)
        },
        trailingContent = {
            DocumentOverflowMenu(
                title = document.title,
                attribution = document.attribution,
                assetFileName = document.assetFileName,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    )
    HorizontalDivider()
}
