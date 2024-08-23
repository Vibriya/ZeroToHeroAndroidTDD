package ru.easycode.zerotoheroandroidtdd.folder.list

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FolderListLayoutBinding
import ru.easycode.zerotoheroandroidtdd.main.FoldersAdapter
import ru.easycode.zerotoheroandroidtdd.main.InteractAdapter

class FoldersListFragment : AbstractFragment<FolderListLayoutBinding, FolderListViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FoldersAdapter {
            viewModel.folderDetails(it)
        }
        binding.foldersRecyclerView.adapter = adapter
        binding.addButton.setOnClickListener {
            viewModel.addFolder()
        }
        FolderListLiveDataWrapper.Base.liveData.observe(viewLifecycleOwner) {
            adapter.add(it)
        }
        viewModel.init()
    }

//    override fun onResume() {
//        super.onResume()
//        binding.foldersRootLayout.addView(binding.foldersTitleTextView)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        binding.foldersRootLayout.removeView(binding.foldersTitleTextView)
//    }
}