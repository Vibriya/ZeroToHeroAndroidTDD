package ru.easycode.zerotoheroandroidtdd.folder.list

import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsFragment
import ru.easycode.zerotoheroandroidtdd.main.Screen

object FoldersListScreen : Screen.Replace(FoldersListFragment::class.java) {
    override fun consume(fragmentManager: FragmentManager, containerId: Int) {
        fragmentManager.popBackStack(
            FolderDetailsFragment::class.java.simpleName,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        super.consume(fragmentManager, containerId)
    }
}