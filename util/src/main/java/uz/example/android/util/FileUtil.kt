package uz.example.android.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileUtil {

    fun getFiles(
        directoryName: String, files: MutableList<File> = mutableListOf()
    ): MutableList<File> {
        val directory = File(directoryName)

        if (directory.listFiles() == null) return files

        val fList = directory.listFiles().orEmpty()
        for (file in fList) {
            if (file.isFile) {
                files.add(file)
            } else if (file.isDirectory) {
                getFiles(file.absolutePath, files)
            }
        }

        return files
    }

    fun createFile(activity: Activity, fileUri: Uri): File? = when (fileUri.scheme) {
        "file" -> fileUri.toFile()
        "content" -> createFileFromContentUri(activity, fileUri)
        else -> null
    }

    fun createFileFromContentUri(activity: Activity, fileUri: Uri): File {
        var fileName = ""
        fileUri.let { returnUri ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity.contentResolver.query(returnUri, null, null, null)
            } else {
                activity.contentResolver.query(returnUri, null, null, null, null)
            }
        }?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            fileName = cursor.getString(nameIndex)
        }

        val iStream = activity.contentResolver.openInputStream(fileUri)!!
        val outputDir = activity.filesDir
        val outputFile = File(outputDir, fileName)
        val outputStream = FileOutputStream(outputFile)
        iStream.copyTo(out = outputStream, bufferSize = 4 * 1024)
        iStream.close()
        return outputFile
    }

    fun clearAppCache(context: Context) {
        try {
            val dir = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun bitmapToUri(context: Context, bmp: Bitmap?): Uri? {
        var bmpUri: Uri? = null
        try {
            val imagePath = File(context.filesDir, "images")
            if (!imagePath.exists()) {
                imagePath.mkdir()
            }
            val file = File(imagePath, System.currentTimeMillis().toString() + ".jpg")
            val out = FileOutputStream(file)
            bmp?.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.close()
            bmpUri = FileProvider.getUriForFile(
                context, "uz.example.android.provider", file
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in 0 until (children?.size ?: 0)) {
                children?.getOrNull(i)?.let { child ->
                    val isSuccess = deleteDir(File(dir, child))
                    if (!isSuccess) {
                        return false
                    }
                }
            }
            return dir.delete()
        } else if (dir != null && dir.isFile) {
            return dir.delete()
        } else {
            return false
        }
    }
}
