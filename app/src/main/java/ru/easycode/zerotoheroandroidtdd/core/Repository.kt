package ru.easycode.zerotoheroandroidtdd.core

interface Repository {

    interface Add {
        fun add(value: String)
    }

    interface Read {
        fun list() : List<String>
    }

    interface Mutable : Add, Read

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : Mutable {

        override fun add(value: String) {
            dataSource.add(ItemCache(now.nowMillis(), value))
        }

        override fun list(): List<String> = dataSource.list().map { it.text }

    }

}
