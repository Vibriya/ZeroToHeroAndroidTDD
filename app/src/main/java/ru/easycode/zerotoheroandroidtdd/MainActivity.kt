package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val list: MutableList<TextView> = mutableListOf()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            binding.contentLayout.addView(
                TextView(this).apply {
                    freezesText = true
                    text = binding.inputEditText.text
                }.also {
                    binding.inputEditText.text?.clear()
                    list.add(it)
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(
            KEY,
            ArrayList(list.map { it.text.toString() })
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredList = savedInstanceState.getStringArrayList(KEY)
        restoredList?.let {
            it.forEach { str ->
                list.add(TextView(this).apply { text = str })
                binding.contentLayout.addView(list.last())
            }
        }
    }

    companion object {
        private const val KEY = "KEY"
    }
}