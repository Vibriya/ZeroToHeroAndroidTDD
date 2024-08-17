package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.Item

interface Repository {

    interface Add {
        fun add(value: String): Long
    }

    interface Read {
        fun list() : List<Item>
    }

    interface Delete {
        fun delete(id: Long)
        fun item(id: Long): Item
    }

    interface Mutable: Add, Read

    interface All : Mutable, Delete

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : All {

        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }

        override fun list(): List<Item> = dataSource.list().map { Item(it.id, it.text) }

        override fun item(id: Long): Item {
            val item = dataSource.item(id)
            return Item(item.id, item.text)
        }

        override fun delete(id: Long) = dataSource.delete(id)

    }



}
