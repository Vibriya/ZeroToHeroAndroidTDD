package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteCache)

    @Query("SELECT * FROM note_cache WHERE note_cache.folderId=:folderId")
    suspend fun notes(folderId: Long): List<NoteCache>

    @Query("DELETE FROM note_cache WHERE note_cache.id=:noteId")
    suspend fun delete(noteId: Long)

    @Query("SELECT * FROM note_cache WHERE note_cache.id=:noteId LIMIT 1")
    suspend fun note(noteId: Long): NoteCache

    @Query("DELETE FROM note_cache WHERE note_cache.folderId=:folderId")
    suspend fun deleteByFolderId(folderId: Long)

}
