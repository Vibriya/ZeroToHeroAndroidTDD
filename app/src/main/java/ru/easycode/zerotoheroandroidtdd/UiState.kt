package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {
    fun dispatch(actionButton: Button, titleTextView: TextView, progress: ProgressBar)
    object Empty : UiState {
        private fun readResolve(): Any = Empty
        override fun dispatch(
            actionButton: Button,
            titleTextView: TextView,
            progress: ProgressBar
        ) = Unit
    }

    object ShowProgress : UiState {
        private fun readResolve(): Any = ShowProgress
        override fun dispatch(
            actionButton: Button,
            titleTextView: TextView,
            progress: ProgressBar
        ) {
            progress.visibility = View.VISIBLE
            actionButton.isEnabled = false
        }
    }

    data class ShowData(private val text: String) : UiState {
        override fun dispatch(
            actionButton: Button,
            titleTextView: TextView,
            progress: ProgressBar
        ) {
            titleTextView.text = text
            titleTextView.visibility = View.VISIBLE
            progress.visibility = View.GONE
            actionButton.isEnabled = true
        }
    }
}
