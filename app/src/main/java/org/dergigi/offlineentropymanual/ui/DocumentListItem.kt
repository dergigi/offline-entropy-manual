package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.dergigi.offlineentropymanual.data.ManualDocument
import org.dergigi.offlineentropymanual.data.openPdfExternally
import org.dergigi.offlineentropymanual.data.openUrl

@Composable
fun DocumentListItem(
    document: ManualDocument,
    onClick: () -> Unit,
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    ListItem(
        headlineContent = { Text(document.title) },
        supportingContent = {
            Text("${document.subtitle}\n${document.attribution.author}")
        },
        leadingContent = {
            Icon(Icons.AutoMirrored.Outlined.MenuBook, contentDescription = null)
        },
        trailingContent = {
            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Outlined.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("Open source website") },
                        leadingIcon = {
                            Icon(Icons.Outlined.Language, contentDescription = null)
                        },
                        onClick = {
                            menuExpanded = false
                            openUrl(context, document.attribution.websiteUrl)
                        },
                    )
                    DropdownMenuItem(
                        text = { Text("Open source PDF online") },
                        leadingIcon = {
                            Icon(
                                Icons.AutoMirrored.Outlined.OpenInNew,
                                contentDescription = null,
                            )
                        },
                        onClick = {
                            menuExpanded = false
                            openUrl(context, document.attribution.documentUrl)
                        },
                    )
                    DropdownMenuItem(
                        text = { Text("Open in another app") },
                        leadingIcon = {
                            Icon(
                                Icons.AutoMirrored.Outlined.MenuBook,
                                contentDescription = null,
                            )
                        },
                        onClick = {
                            menuExpanded = false
                            openPdfExternally(context, document.assetFileName)
                        },
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    )
    HorizontalDivider()
}
