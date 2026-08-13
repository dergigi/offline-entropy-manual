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
    val assetLength = runCatching {
        context.assets.openFd(assetFileName).use { it.length }
    }.getOrNull()
    val needsCopy = when {
        !outFile.exists() || outFile.length() <= 0L -> true
        assetLength != null -> outFile.length() != assetLength
        else -> true
    }
    if (needsCopy) {
        context.assets.open(assetFileName).use { input ->
            FileOutputStream(outFile).use { output ->
                input.copyTo(output)
            }
        }
    }
    return outFile
}

private fun pdfCacheUri(context: Context, assetFileName: String): Uri {
    val file = copyAssetToCache(context, assetFileName)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file,
    )
}

fun openPdfExternally(context: Context, assetFileName: String) {
    val viewIntent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(pdfCacheUri(context, assetFileName), "application/pdf")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    try {
        context.startActivity(Intent.createChooser(viewIntent, "Open with"))
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(context, "No PDF viewer installed", Toast.LENGTH_SHORT).show()
    }
}

fun sharePdf(context: Context, assetFileName: String, title: String? = null) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, pdfCacheUri(context, assetFileName))
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        if (!title.isNullOrBlank()) {
            putExtra(Intent.EXTRA_SUBJECT, title)
        }
    }
    try {
        context.startActivity(Intent.createChooser(shareIntent, "Share"))
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(context, "Nothing to share with", Toast.LENGTH_SHORT).show()
    }
}

fun openUrl(context: Context, url: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(context, "No browser available", Toast.LENGTH_SHORT).show()
    }
}
