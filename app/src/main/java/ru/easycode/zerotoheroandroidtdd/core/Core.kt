package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.room.Room

interface Core {

    class Base(context: Context) : Core {
        private val database by lazy {
            Room.databaseBuilder(context, AppDataBase::class.java, "ItemsDataBase").build()
        }

    }
}