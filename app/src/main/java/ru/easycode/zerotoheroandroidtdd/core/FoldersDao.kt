package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoldersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(folder: FolderCache)

    @Query("SELECT * FROM folder_cache")
    suspend fun folders(): List<FolderCache>

    @Query("DELETE FROM folder_cache WHERE folder_cache.id=:folderId")
    suspend fun delete(folderId: Long)

}
