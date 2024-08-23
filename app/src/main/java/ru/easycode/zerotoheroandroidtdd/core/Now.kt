package ru.easycode.zerotoheroandroidtdd.core

interface Now {
    fun timeInMillis(): Long

    object Base : Now {
        override fun timeInMillis(): Long = System.currentTimeMillis()
    }
}
