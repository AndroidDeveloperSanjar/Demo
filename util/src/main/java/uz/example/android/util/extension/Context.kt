package uz.example.android.util.extension

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.PersistableBundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

fun Context.color(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.colorStateList(@ColorRes colorRes: Int) =
    ContextCompat.getColorStateList(this, colorRes)

fun Context.drawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

private var toast: WeakReference<Toast?>? = null

@SuppressLint("ShowToast")
fun Context.toast(msg: Any?, isShort: Boolean = true) {
    msg?.let {
        if (toast?.get() == null) {
            toast = WeakReference(Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT))
        } else {
            toast?.get()?.setText(msg.toString())
        }
        toast?.get()?.run {
            duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                addCallback(object : Toast.Callback() {
                    override fun onToastHidden() {
                        removeCallback(this)
                        toast?.clear()
                        super.onToastHidden()
                    }
                })
            }

            show()
        }
    }
}

fun Context.openBrowser(link: String?, tabColor: Int) {
    if (!link.isNullOrEmpty()) {
        CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams
                    .Builder()
                    .setToolbarColor(tabColor)
                    .build()
            )
            .build()
            .launchUrl(this, Uri.parse(link))
    }
}

fun Context.copyText(text: String, @StringRes toastTextRes: Int?, isSensitive: Boolean = true) {
    val myClipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    val myClip = ClipData.newPlainText("TEXT", text)
    myClip?.let {
        myClipboard?.setPrimaryClip(it)

        if (isSensitive) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.description.extras = PersistableBundle().apply {
                    putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.description.extras = PersistableBundle().apply {
                    putBoolean("android.content.extra.IS_SENSITIVE", true)
                }
            }
        }
    }

    toastTextRes?.let { toastText ->
        Toast.makeText(this, getString(toastText), Toast.LENGTH_SHORT).show()
    }
}

val Context.processName: String?
    get() {
        return (getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager)
            ?.runningAppProcesses
            ?.find { it.pid == android.os.Process.myPid() }
            ?.processName
    }

@SuppressLint("MissingPermission")
fun Context.vibration(duration: Long = 100L) {
    val v = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        v.vibrate(
            VibrationEffect.createOneShot(
                duration,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        @Suppress("DEPRECATION")
        v.vibrate(duration)
    }
}