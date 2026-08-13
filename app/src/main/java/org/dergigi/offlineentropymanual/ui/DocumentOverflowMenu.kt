package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import org.dergigi.offlineentropymanual.data.Attribution
import org.dergigi.offlineentropymanual.data.openPdfExternally
import org.dergigi.offlineentropymanual.data.openUrl
import org.dergigi.offlineentropymanual.data.sharePdf

@Composable
fun DocumentOverflowMenu(
    title: String,
    attribution: Attribution,
    assetFileName: String,
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Outlined.MoreVert, contentDescription = "More")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = { Text("Open source website") },
                leadingIcon = {
                    Icon(Icons.Outlined.Language, contentDescription = null)
                },
                onClick = {
                    expanded = false
                    openUrl(context, attribution.websiteUrl)
                },
            )
            DropdownMenuItem(
                text = { Text("Open source PDF online") },
                leadingIcon = {
                    Icon(Icons.AutoMirrored.Outlined.OpenInNew, contentDescription = null)
                },
                onClick = {
                    expanded = false
                    openUrl(context, attribution.documentUrl)
                },
            )
            DropdownMenuItem(
                text = { Text("Open in another app") },
                leadingIcon = {
                    Icon(Icons.AutoMirrored.Outlined.MenuBook, contentDescription = null)
                },
                onClick = {
                    expanded = false
                    openPdfExternally(context, assetFileName)
                },
            )
            DropdownMenuItem(
                text = { Text("Share") },
                leadingIcon = {
                    Icon(Icons.Outlined.Share, contentDescription = null)
                },
                onClick = {
                    expanded = false
                    sharePdf(context, assetFileName, title)
                },
            )
        }
    }
}
