package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.room.Room

interface Core {
    fun repository() : Repository.All

    class Base(context: Context) : Core {
        private val database by lazy {
            Room.databaseBuilder(context, ItemsDataBase::class.java, "ItemsDataBase").build()
        }

        private val repository: Repository.All by lazy {
            Repository.Base(database.itemsDao(), Now.Base)
        }

        override fun repository(): Repository.All = repository

    }
}