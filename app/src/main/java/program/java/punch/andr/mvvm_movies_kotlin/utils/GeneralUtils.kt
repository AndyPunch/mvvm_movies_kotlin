package program.java.punch.andr.mvvm_movies_kotlin.utils

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethodManager


object GeneralUtils {

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager? =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val focusedView: View? = activity.currentFocus!!
        if (inputMethodManager != null && focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
        }

    }

    fun showSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    fun showErrorDialog(context: Context, message: String, onDismiss: () -> Unit = {}) {
        AlertDialog.Builder(context).setMessage(message)
            .setPositiveButton("OK", { d, w -> onDismiss() })
            .show()
    }
}