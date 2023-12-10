package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionButton = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        progressBar.visibility = View.GONE
        titleTextView.visibility = View.GONE

        actionButton.setOnClickListener {
            it.isEnabled = false
            progressBar.visibility = View.VISIBLE

            Thread {
                Thread.sleep(3500)
                runOnUiThread {
                    titleTextView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    it.isEnabled = true
                }
            }.start()

        }
    }
}