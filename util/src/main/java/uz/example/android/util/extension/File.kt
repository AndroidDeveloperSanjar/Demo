package uz.example.android.util.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel

fun File.toBitmap(): Bitmap = BitmapFactory.decodeFile(absolutePath)

@Suppress("UNREACHABLE_CODE")
fun Uri.getAbsolutePathFile(context: Context): String? {
    val ins = context.contentResolver?.openInputStream(this)
    val fileExtension: String = this.pathSegments.last()
    try {
        val file = File(context.filesDir, fileExtension)
        if (file.exists()) {
            if (file.parentFile?.mkdirs() == true) {
                val fileOutputStream = FileOutputStream(file)
                ins?.copyTo(fileOutputStream)
                ins?.close()
                fileOutputStream.close()
            }
            return file.absolutePath
        } else {
            file.createNewFile()
            val fileOutputStream = FileOutputStream(file)
            ins?.copyTo(fileOutputStream)
            ins?.close()
            fileOutputStream.close()
            return file.absolutePath
        }
    } catch (e: Exception) {
        return null
    }

    return null
}

fun writeBitmapToFile(bitmap: Bitmap, context: Context, imagePath: String): File? {
    return try {
        val file = File(context.cacheDir, imagePath)
        file.createNewFile()
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
        val bimapData = bos.toByteArray()
        val fos = FileOutputStream(file)
        fos.write(bimapData)
        fos.flush()
        fos.close()
        file
    } catch (e: Exception) {
        null
    }
}

fun deleteFileWithPath(context: Context, imagePath: String) {
    val file = File(context.cacheDir, imagePath)
    file.delete()
}

fun File.copy(dest: File) {
    var fi: FileInputStream? = null
    var fo: FileOutputStream? = null
    var ic: FileChannel? = null
    var oc: FileChannel? = null
    try {
        if (!dest.exists()) {
            dest.createNewFile()
        }
        fi = FileInputStream(this)
        fo = FileOutputStream(dest)
        ic = fi.channel
        oc = fo.channel
        ic.transferTo(0, ic.size(), oc)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fi?.close()
        fo?.close()
        ic?.close()
        oc?.close()
    }
}

fun File.move(dest: File) {
    copy(dest)
    delete()
}

fun File.copyDirectory(dest: File) {
    if (!dest.exists()) {
        dest.mkdirs()
    }
    val files = listFiles()
    files?.forEach {
        if (it.isFile) {
            it.copy(File("${dest.absolutePath}/${it.name}"))
        }
        if (it.isDirectory) {
            val dirSrc = File("$absolutePath/${it.name}")
            val dirDest = File("${dest.absolutePath}/${it.name}")
            dirSrc.copyDirectory(dirDest)
        }
    }
}

fun File.moveDirectory(dest: File) {
    copyDirectory(dest)
    deleteAll()
}

fun File.deleteAll() {
    if (isFile && exists()) {
        delete()
        return
    }
    if (isDirectory) {
        val files = listFiles()
        if (files == null || files.isEmpty()) {
            delete()
            return
        }
        files.forEach { it.deleteAll() }
        delete()
    }
}

fun File.toByteArray(): ByteArray {
    val bos = ByteArrayOutputStream(this.length().toInt())
    val input = FileInputStream(this)
    val size = 1024
    val buffer = ByteArray(size)
    var len = input.read(buffer, 0, size)
    while (len != -1) {
        bos.write(buffer, 0, len)
        len = input.read(buffer, 0, size)
    }
    input.close()
    bos.close()
    return bos.toByteArray()
}
