package uz.example.android.util.extension

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorRes

fun SpannableString.withClickableSpan(
    context: Context,
    clickablePart: String,
    @ColorRes colorRes: Int,
    onClick: () -> Unit
): SpannableString {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClick.invoke()
        }
    }

    val clickablePartStart = indexOf(clickablePart)

    setSpan(
        clickableSpan,
        clickablePartStart,
        clickablePartStart + clickablePart.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    setSpan(
        ForegroundColorSpan(context.color(colorRes)),
        clickablePartStart,
        clickablePartStart + clickablePart.length,
        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )

    return this
}

fun SpannableString.highlightText(
    context: Context,
    @ColorRes colorRes: Int,
    startIndex: Int,
    endIndex: Int
): SpannableString {
    setSpan(
        ForegroundColorSpan(context.color(colorRes)),
        startIndex,
        endIndex,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this
}

fun SpannableString.removeForegroundSpans(): SpannableString {
    val spans = this.getSpans(0, this.length, ForegroundColorSpan::class.java)
    spans.forEach {
        this.removeSpan(it)
    }
    return this
}
