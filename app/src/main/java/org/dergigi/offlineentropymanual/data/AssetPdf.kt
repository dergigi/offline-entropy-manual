package org.dergigi.offlineentropymanual.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun copyAssetToCache(context: Context, assetFileName: String): File {
    val outFile = File(context.cacheDir, assetFileName)
    if (outFile.exists() && outFile.length() > 0L) {
        return outFile
    }
    context.assets.open(assetFileName).use { input ->
        FileOutputStream(outFile).use { output ->
            input.copyTo(output)
        }
    }
    return outFile
}

fun openPdfExternally(context: Context, assetFileName: String) {
    val file = copyAssetToCache(context, assetFileName)
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file,
    )
    val viewIntent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    try {
        context.startActivity(Intent.createChooser(viewIntent, "Open with"))
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(context, "No PDF viewer installed", Toast.LENGTH_SHORT).show()
    }
}

fun openUrl(context: Context, url: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(context, "No browser available", Toast.LENGTH_SHORT).show()
    }
}
