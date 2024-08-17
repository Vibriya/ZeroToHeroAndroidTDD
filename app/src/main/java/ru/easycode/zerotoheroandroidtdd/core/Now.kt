package ru.easycode.zerotoheroandroidtdd.core

interface Now {
    fun nowMillis(): Long

    object Base : Now {
        override fun nowMillis(): Long = System.currentTimeMillis()
    }
}
