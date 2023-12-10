package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var button:Button
    private lateinit var buttonDec:Button
    private lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.incrementButton)
        buttonDec = findViewById(R.id.decrementButton)
        textView = findViewById(R.id.countTextView)

        if (savedInstanceState == null) textView.text = (0).toString()
        button.isEnabled = (application as App).addButtonEnabled
        buttonDec.isEnabled = (application as App).decButtonEnabled

        button.setOnClickListener {
            val cur = textView.text.toString().toInt() + 2
            textView.text = cur.toString()
            changeButtonsStatus(cur)
        }

        buttonDec.setOnClickListener {
            val cur = textView.text.toString().toInt() - 2
            textView.text = cur.toString()
            changeButtonsStatus(cur)
        }
    }

    private fun changeButtonsStatus(curInt: Int){
        (application as App).decButtonEnabled = true
        (application as App).addButtonEnabled = true

        if (curInt == 0){
            (application as App).decButtonEnabled = false
        } else if(curInt == 4) {
            (application as App).addButtonEnabled = false
        }

        button.isEnabled = (application as App).addButtonEnabled
        buttonDec.isEnabled = (application as App).decButtonEnabled

    }
}