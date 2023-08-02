package uz.example.android.util.extension

import android.content.Context
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AnimRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

fun View.singleClick(l: View.OnClickListener) {
    setOnClickListener(SingleClickListener(l))
}

internal class SingleClickListener(private inline val clickListener: View.OnClickListener) :
    View.OnClickListener {

    private var lastClick: Long = 0

    override fun onClick(v: View) {
        if (getLastClickTimeout() > DOUBLE_CLICK_TIMEOUT) {
            lastClick = System.currentTimeMillis()
            clickListener.onClick(v)
        }
    }

    private fun getLastClickTimeout() = System.currentTimeMillis() - lastClick

    companion object {

        private const val DOUBLE_CLICK_TIMEOUT = 500
    }
}

fun View.showKeyboard(requestFocus: Boolean = true) {
    if (requestFocus) requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboardIfFocused() {
    if (isFocusable || isFocused) hideKeyboard()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.keyboardVisibilityChanges() =
    onPreDrawFlow()
        .map { isKeyboardVisible() }
        .distinctUntilChanged()

fun View.onPreDrawFlow() = callbackFlow {
    val onPreDrawListener = ViewTreeObserver.OnPreDrawListener {
        trySendBlocking(Unit)
        true
    }
    viewTreeObserver.addOnPreDrawListener(onPreDrawListener)
    awaitClose {
        viewTreeObserver.removeOnPreDrawListener(onPreDrawListener)
    }
}

fun View.isKeyboardVisible() = ViewCompat.getRootWindowInsets(this)
    ?.isVisible(WindowInsetsCompat.Type.ime())
    ?: false

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.animate(@AnimRes id: Int) = startAnimation(AnimationUtils.loadAnimation(context, id))

fun View.slideDown(root: ViewGroup) {
    val transition = Slide(Gravity.BOTTOM)
    transition.duration = 600L
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(root, transition)
    visibility = View.GONE
}

fun View.slideUp(root: ViewGroup) {
    val transition = Slide(Gravity.BOTTOM)
    transition.duration = 600L
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(root, transition)
    visibility = View.VISIBLE
}
