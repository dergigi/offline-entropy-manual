package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.dergigi.offlineentropymanual.data.ContentLicense
import org.dergigi.offlineentropymanual.data.openUrl

@Composable
fun LinkText(
    url: String,
    label: String = url,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Text(
        text = label,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .clickable { openUrl(context, url) }
            .padding(vertical = 2.dp),
    )
}

@Composable
fun LicenseLink(
    license: ContentLicense,
    modifier: Modifier = Modifier,
) {
    val label = "License: ${license.label}"
    val url = license.url
    if (url != null) {
        LinkText(url = url, label = label, modifier = modifier)
    } else {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = modifier.padding(vertical = 2.dp),
        )
    }
}
