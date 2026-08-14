package org.dergigi.offlineentropymanual.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.PrintManager
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream
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

fun printPdf(context: Context, assetFileName: String, title: String) {
    val file = copyAssetToCache(context, assetFileName)
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as? PrintManager
    if (printManager == null) {
        Toast.makeText(context, "Printing is not available", Toast.LENGTH_SHORT).show()
        return
    }
    val jobName = title.ifBlank { assetFileName }
    try {
        printManager.print(
            jobName,
            PdfFilePrintAdapter(file, jobName),
            PrintAttributes.Builder().build(),
        )
    } catch (e: Exception) {
        Toast.makeText(
            context,
            e.message?.takeIf { it.isNotBlank() } ?: "Could not start print",
            Toast.LENGTH_SHORT,
        ).show()
    }
}

private class PdfFilePrintAdapter(
    private val file: File,
    private val documentName: String,
) : PrintDocumentAdapter() {
    override fun onLayout(
        oldAttributes: PrintAttributes?,
        newAttributes: PrintAttributes,
        cancellationSignal: CancellationSignal?,
        callback: LayoutResultCallback,
        extras: Bundle?,
    ) {
        if (cancellationSignal?.isCanceled == true) {
            callback.onLayoutCancelled()
            return
        }
        val info = PrintDocumentInfo.Builder(documentName)
            .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
            .setPageCount(PrintDocumentInfo.PAGE_COUNT_UNKNOWN)
            .build()
        callback.onLayoutFinished(info, newAttributes != oldAttributes)
    }

    override fun onWrite(
        pages: Array<PageRange>,
        destination: ParcelFileDescriptor,
        cancellationSignal: CancellationSignal?,
        callback: WriteResultCallback,
    ) {
        try {
            FileInputStream(file).use { input ->
                FileOutputStream(destination.fileDescriptor).use { output ->
                    val buffer = ByteArray(8192)
                    while (true) {
                        if (cancellationSignal?.isCanceled == true) {
                            callback.onWriteCancelled()
                            return
                        }
                        val read = input.read(buffer)
                        if (read < 0) break
                        output.write(buffer, 0, read)
                    }
                }
            }
            callback.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
        } catch (e: Exception) {
            callback.onWriteFailed(e.message)
        }
    }
}

fun openUrl(context: Context, url: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(context, "No browser available", Toast.LENGTH_SHORT).show()
    }
}
