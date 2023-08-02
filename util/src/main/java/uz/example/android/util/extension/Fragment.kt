package uz.example.android.util.extension

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.example.android.util.FileUtil
import java.io.File

fun Fragment.navigate(
    actionId: Int
) = findNavController().navigate(actionId)

fun Fragment.navigate(actionId: Int, arg: Bundle) = findNavController().navigate(actionId, arg)

fun Fragment.navigate(direction: NavDirections) = findNavController().navigate(direction)

fun Fragment.navigate(direction: NavDirections, extras: FragmentNavigator.Extras) =
    findNavController().navigate(direction, extras)

fun Fragment.color(@ColorRes colorRes: Int) = requireContext().color(colorRes)

fun Fragment.drawable(@DrawableRes drawableRes: Int) = requireContext().drawable(drawableRes)

fun Fragment.openBrowser(link: String?, tabColor: Int) =
    requireContext().openBrowser(link, tabColor)

fun Fragment.toast(message: Any, isShort: Boolean = true) =
    requireContext().toast(message, isShort)

fun Fragment.hideKeyboard() {
    requireActivity().window.peekDecorView()?.hideKeyboard()
}

fun Fragment.withDelay(delay: Long, action: () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        delay(delay)
        action.invoke()
    }
}

fun Fragment.share(text: String?, bitmap: Bitmap?, @StringRes chooserTitleTextRes: Int) {
    val imageUri = FileUtil.bitmapToUri(requireContext(), bitmap)
    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        putExtra(Intent.EXTRA_STREAM, imageUri)
        type = "image/*"
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    startActivity(
        Intent.createChooser(
            shareIntent,
            resources.getText(chooserTitleTextRes)
        )
    )
}

fun Fragment.shareText(text: String, @StringRes chooserTitleTextRes: Int) {
    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    startActivity(
        Intent.createChooser(
            shareIntent,
            resources.getText(chooserTitleTextRes)
        )
    )
}

fun Fragment.sharePDF(file: File, providerAuthority: String) {
    val uri = FileProvider.getUriForFile(
        requireContext(),
        providerAuthority,
        file
    )

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
    shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    shareIntent.type = "application/pdf"
    startActivity(Intent.createChooser(shareIntent, "share.."))
}