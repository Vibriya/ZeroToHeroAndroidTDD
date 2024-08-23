package ru.easycode.zerotoheroandroidtdd.note.core

import ru.easycode.zerotoheroandroidtdd.core.NoteCache
import ru.easycode.zerotoheroandroidtdd.core.NotesDao
import ru.easycode.zerotoheroandroidtdd.core.Now

interface NotesRepository {

    interface Create {
        suspend fun createNote(folderId: Long, text: String): Long
    }

    interface Edit {
        suspend fun deleteNote(noteId: Long)
        suspend fun renameNote(noteId: Long, newName: String)
        suspend fun note(noteId: Long): MyNote
    }

    interface ReadList {
        suspend fun noteList(folderId: Long): List<MyNote>
    }

    class Base(
        private val now: Now,
        private val dao: NotesDao
    ) : Create, Edit, ReadList {

        override suspend fun noteList(folderId: Long): List<MyNote> =
            dao.notes(folderId).map { MyNote(it.id, it.text, it.folderId) }

        override suspend fun renameNote(noteId: Long, newName: String) =
            dao.note(noteId).let { dao.insert(NoteCache(it.id, newName, it.folderId)) }

        override suspend fun note(noteId: Long): MyNote =
            dao.note(noteId).run { MyNote(id, text, folderId) }

        override suspend fun createNote(folderId: Long, text: String): Long =
            now.timeInMillis().also { dao.insert(NoteCache(it, text, folderId)) }

        override suspend fun deleteNote(noteId: Long) = dao.delete(noteId)

    }

}
