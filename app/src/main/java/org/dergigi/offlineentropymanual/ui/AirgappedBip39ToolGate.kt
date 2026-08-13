package org.dergigi.offlineentropymanual.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.dergigi.offlineentropymanual.data.openUrl

const val IanColemanBip39ReleasesUrl =
    "https://github.com/iancoleman/bip39/releases"

private val Confirmations = listOf(
    "I will only run this tool on an airgapped computer.",
    "I will not enter a real seed (or partial seed) on this phone or any online device.",
    "I understand a mistake here can lose all my bitcoin.",
    "I know what I am doing.",
)

@Composable
fun AirgappedBip39ToolGate(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val checked = remember {
        mutableStateListOf(*BooleanArray(Confirmations.size) { false }.toTypedArray())
    }
    val allChecked = checked.all { it }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Advanced: airgapped computer",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "If you know what you are doing, you can calculate the checksum word " +
                "with Ian Coleman's BIP39 tool on an airgapped computer. Download only " +
                "from the official GitHub releases page. Prefer a hardware wallet when " +
                "you can.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp),
        )
        Confirmations.forEachIndexed { index, label ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp),
            ) {
                Checkbox(
                    checked = checked[index],
                    onCheckedChange = { checked[index] = it },
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
        }
        Button(
            onClick = { openUrl(context, IanColemanBip39ReleasesUrl) },
            enabled = allChecked,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
        ) {
            Text("Open Ian Coleman BIP39 releases")
        }
    }
}
