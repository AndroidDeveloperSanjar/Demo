package uz.example.android.util.extension

import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DISMISS_DELAY = 100L

fun BottomSheetBehavior<*>.show() {
    state = BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetBehavior<*>.hide() {
    state = BottomSheetBehavior.STATE_HIDDEN
}

fun BottomSheetDialogFragment.showFull(isShowFull: Boolean = true) {
    if (isShowFull) {
        dialog?.setOnShowListener { dialog ->
            val d = dialog as? BottomSheetDialog
            val bottomSheet =
                d?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as? FrameLayout
            val bottomSheetBehavior = bottomSheet?.let { BottomSheetBehavior.from(it) }
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}

fun BottomSheetDialogFragment.dismissWithDelay(delay: Long = DISMISS_DELAY) {
    lifecycleScope.launch {
        delay(delay)
        dismiss()
    }
}
