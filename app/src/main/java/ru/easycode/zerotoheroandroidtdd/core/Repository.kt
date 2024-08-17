package ru.easycode.zerotoheroandroidtdd.core

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

    interface Update {
        fun update(id: Long, newText: String)
    }

    interface Mutable: Add, Read

    interface Change: Update, Delete

    interface All : Mutable, Change

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

        override fun update(id: Long, newText: String) {
            dataSource.add(ItemCache(id, newText))
        }

        override fun delete(id: Long) = dataSource.delete(id)

    }


}
