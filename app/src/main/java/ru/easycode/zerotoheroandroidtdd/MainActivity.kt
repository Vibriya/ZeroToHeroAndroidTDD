package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionButton = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)

        viewModel.liveData().observe(this) {
            state -> state.show(actionButton, progressBar, titleTextView)
        }

        actionButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.init(savedInstanceState == null)
    }
}