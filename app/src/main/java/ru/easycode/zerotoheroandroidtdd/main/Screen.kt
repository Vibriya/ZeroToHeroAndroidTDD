package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

interface Screen {
    fun consume(fragmentManager: FragmentManager, containerId: Int)

    abstract class Add(private val clazz: Class<out Fragment>) : Screen {
        override fun consume(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .add(containerId, clazz.getDeclaredConstructor().newInstance())
                .addToBackStack(clazz.simpleName)
                .commit()
        }
    }

    abstract class AddBundleable(private val clazz: Class<out Fragment>) : Screen {
        abstract val bundle: Bundle
        override fun consume(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .add(containerId, clazz, bundle)
                .addToBackStack(clazz.simpleName)
                .commit()
        }
    }

    abstract class Replace(private val clazz: Class<out Fragment>) : Screen {
        override fun consume(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .replace(containerId, clazz.getDeclaredConstructor().newInstance())
                .commit()
        }
    }

    abstract class ReplaceBackstack(private val clazz: Class<out Fragment>) : Screen {
        override fun consume(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .replace(containerId, clazz.getDeclaredConstructor().newInstance())
                .addToBackStack(clazz.simpleName)
                .commit()
        }
    }

    abstract class ReplaceBackstackBundleable(private val clazz: Class<out Fragment>) : Screen {
        abstract val bundle: Bundle
        override fun consume(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .replace(containerId, clazz, bundle)
                .addToBackStack(clazz.simpleName)
                .commit()
        }
    }
}
