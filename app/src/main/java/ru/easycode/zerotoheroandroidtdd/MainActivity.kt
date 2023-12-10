package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.incrementButton)
        val textView = findViewById<TextView>(R.id.countTextView)

        if (savedInstanceState == null) textView.text = (0).toString()
        button.isEnabled = (application as App).buttonEnabled

        button.setOnClickListener {
            val cur = textView.text.toString().toInt() + 2
            textView.text = cur.toString()

            if (cur == 4) {
                (application as App).buttonEnabled = false
                it.isEnabled = (application as App).buttonEnabled
            }
        }
    }
}