package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button:Button = findViewById(R.id.incrementButton)
        val buttonDec:Button = findViewById(R.id.decrementButton)
        val textView:TextView = findViewById(R.id.countTextView)

        if (savedInstanceState == null) textView.text = (0).toString()
        button.isEnabled = (application as App).addButtonEnabled
        buttonDec.isEnabled = (application as App).decButtonEnabled

        button.setOnClickListener {
            val cur = textView.text.toString().toInt() + 2
            textView.text = cur.toString()

            (application as App).addButtonEnabled = cur != 4
            button.isEnabled = (application as App).addButtonEnabled

            (application as App).decButtonEnabled = true
            buttonDec.isEnabled = (application as App).decButtonEnabled
        }

        buttonDec.setOnClickListener {
            val cur = textView.text.toString().toInt() - 2
            textView.text = cur.toString()

            (application as App).decButtonEnabled = cur != 0
            buttonDec.isEnabled = (application as App).decButtonEnabled

            (application as App).addButtonEnabled = true
            button.isEnabled = (application as App).addButtonEnabled
        }
    }
}