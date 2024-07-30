package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionButton = findViewById<Button>(R.id.actionButton)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val inputEditText = findViewById<TextInputEditText>(R.id.inputEditText)
        
        viewModel.liveData().observe(this) {
            it.dispatch(actionButton, titleTextView, inputEditText)
        }
        actionButton.setOnClickListener {
            viewModel.click(inputEditText.text.toString())
        }

        inputEditText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    titleTextView.text = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    Toast.makeText(this@MainActivity, "afterTextChanged", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restore(BundleWrapper.Base(savedInstanceState))
    }
}