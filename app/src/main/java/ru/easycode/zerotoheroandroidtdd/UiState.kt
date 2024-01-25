package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {
    fun show(button: Button, progressBar: ProgressBar, textView: TextView)
    object Initial : UiState {
        override fun show(button: Button, progressBar: ProgressBar, textView: TextView) {
            button.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            textView.visibility = View.GONE
        }
    }

    object ShowProgress : UiState {
        override fun show(button: Button, progressBar: ProgressBar, textView: TextView) {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            textView.visibility = View.GONE
        }
    }

    object ShowData : UiState {
        override fun show(button: Button, progressBar: ProgressBar, textView: TextView) {
            button.isEnabled = true
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }
    }
}