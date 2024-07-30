package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.io.Serializable

interface UiState : Serializable {
    fun dispatch(actionButton: Button, titleTextView: TextView, inputEditText: TextInputEditText)

    object Empty : UiState {
        private fun readResolve(): Any = Empty
        override fun dispatch(
            actionButton: Button,
            titleTextView: TextView,
            inputEditText: TextInputEditText
        ) = Unit
    }

    object ShowProgress : UiState {
        private fun readResolve(): Any = ShowProgress
        override fun dispatch(
            actionButton: Button,
            titleTextView: TextView,
            inputEditText: TextInputEditText
        ) {

        }
    }

    data class ShowData(private val text: String) : UiState {
        override fun dispatch(
            actionButton: Button,
            titleTextView: TextView,
            inputEditText: TextInputEditText
        ) {
            titleTextView.text = text
            inputEditText.text?.clear()
        }
    }

    data class Error(private val msg: String) : UiState {
        override fun dispatch(
            actionButton: Button,
            titleTextView: TextView,
            inputEditText: TextInputEditText
        ) {

        }

    }
}
