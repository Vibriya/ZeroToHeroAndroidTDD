package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    companion object {
        private const val BUNDLE_TV_REMOVED_TAG = "BUNDLE_TV_REMOVED_TAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.removeButton)
        val textView = findViewById<TextView>(R.id.titleTextView)
        val root = findViewById<LinearLayout>(R.id.rootLayout)

        savedInstanceState?.let {
            val tvHidden = savedInstanceState.getBoolean(BUNDLE_TV_REMOVED_TAG)
            if (tvHidden) root.removeView(textView)
        }

        button.setOnClickListener {
            root.removeView(textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BUNDLE_TV_REMOVED_TAG, true)
    }
}