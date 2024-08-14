package ru.easycode.zerotoheroandroidtdd.main

import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.RAdapter
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper

interface Screen {
    fun observed(
        lifecycleOwner: LifecycleOwner,
        linearLayout: LinearLayout,
        inflater: LayoutInflater,
        createContainer: CreateViewModelContainer,
        listContainer: ListViewModelContainer
    )

    object ListScreen : Screen {
        override fun observed(
            lifecycleOwner: LifecycleOwner,
            linearLayout: LinearLayout,
            inflater: LayoutInflater,
            createContainer: CreateViewModelContainer,
            listContainer: ListViewModelContainer
        ) {
            val frameLayout = inflater.inflate(R.layout.list_frame_layout, linearLayout, false)
            val adapter = RAdapter()
            frameLayout.findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

            frameLayout.findViewById<FloatingActionButton>(R.id.addButton).setOnClickListener {
                Navigation.Base.update(CreateScreen)
            }

            ListLiveDataWrapper.Base.liveData().observe(lifecycleOwner) {
                adapter.addToList(it.map { char -> char.toString() })
            }

            linearLayout.addView(frameLayout)
        }

    }

    object CreateScreen : Screen {
        override fun observed(
            lifecycleOwner: LifecycleOwner,
            linearLayout: LinearLayout,
            inflater: LayoutInflater,
            createContainer: CreateViewModelContainer,
            listContainer: ListViewModelContainer
        ) {
            if (createContainer.viewModel == null) createContainer.initializeViewModel()

            val frameLayout = inflater.inflate(R.layout.create_frame_layout, linearLayout, false)
            val button = frameLayout.findViewById<Button>(R.id.createButton)
            val inputText = frameLayout.findViewById<TextInputEditText>(R.id.inputEditText)

            inputText.addTextChangedListener { text ->
                text?.let {
                    button.isEnabled = it.length >= 3
                }
            }

            button.setOnClickListener {
                inputText.text?.let { text ->
                    createContainer.viewModel?.add(text.toString())
                    text.clear()
                }
            }
            linearLayout.addView(
                frameLayout
            )
        }
    }

    object Pop : Screen {
        override fun observed(
            lifecycleOwner: LifecycleOwner,
            linearLayout: LinearLayout,
            inflater: LayoutInflater,
            createContainer: CreateViewModelContainer,
            listContainer: ListViewModelContainer
        ) {
            linearLayout.removeView(
                linearLayout.findViewById(R.id.createFrameLayout)
            )
            createContainer.viewModel = null
        }
    }

}
