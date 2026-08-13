package org.dergigi.offlineentropymanual.data

import android.content.Context
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
