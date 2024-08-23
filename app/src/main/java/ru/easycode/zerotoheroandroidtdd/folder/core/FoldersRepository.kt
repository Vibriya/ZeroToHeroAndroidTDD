package ru.easycode.zerotoheroandroidtdd.folder.core

import ru.easycode.zerotoheroandroidtdd.core.FolderCache
import ru.easycode.zerotoheroandroidtdd.core.FoldersDao
import ru.easycode.zerotoheroandroidtdd.core.NotesDao
import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.folder.list.Folder

interface FoldersRepository {

    interface ReadList {
        suspend fun folders(): List<Folder>
    }

    interface Create {
        suspend fun createFolder(name: String): Long
    }

    interface Edit {
        suspend fun delete(folderId: Long)

        suspend fun rename(folderId: Long, newName: String)
    }

    class Base(
        private val now: Now,
        private val foldersDao: FoldersDao,
        private val notesDao: NotesDao
    ) : Create, ReadList, Edit {

        override suspend fun createFolder(name: String): Long =
            now.timeInMillis().also { foldersDao.insert(FolderCache(it, name)) }

        override suspend fun folders(): List<Folder>  =
            foldersDao.folders()
            .map { folder ->
                Folder(folder.id, folder.text, notesDao.notes(folder.id).count())
            }

        override suspend fun delete(folderId: Long) {
            foldersDao.delete(folderId)
            notesDao.deleteByFolderId(folderId)
        }

        override suspend fun rename(folderId: Long, newName: String) =
            foldersDao.insert(FolderCache(folderId, newName))

    }

}
