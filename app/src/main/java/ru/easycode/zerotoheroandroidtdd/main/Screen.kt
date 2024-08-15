package ru.easycode.zerotoheroandroidtdd.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun observed(fragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(private val clazz: Class<out Fragment>) : Screen {
        override fun observed(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .replace(containerId, clazz.getDeclaredConstructor().newInstance())
                .commit()
        }
    }

    abstract class Add(private val clazz: Class<out Fragment>) : Screen {
        override fun observed(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .add(containerId, clazz.getDeclaredConstructor().newInstance())
                .addToBackStack(clazz.simpleName)
                .commit()
        }
    }

    object Pop : Screen {
        override fun observed(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.popBackStack()
        }
    }

}
