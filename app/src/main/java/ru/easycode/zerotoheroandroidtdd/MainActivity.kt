package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    companion object {
        private const val BUNDLE_TV_GONE_TAG = "BUNDLE_TV_GONE_TAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.hideButton)
        val textView = findViewById<TextView>(R.id.titleTextView)

        savedInstanceState?.let {
            val tvHidden = savedInstanceState.getBoolean(BUNDLE_TV_GONE_TAG)
            if (tvHidden) textView.visibility = View.GONE
        }

        button.setOnClickListener {
            textView.visibility = View.GONE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BUNDLE_TV_GONE_TAG, true)
    }
}