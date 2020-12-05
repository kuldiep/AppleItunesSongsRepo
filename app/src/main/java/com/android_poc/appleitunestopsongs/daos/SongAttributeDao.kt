package com.android_poc.appleitunestopsongs.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android_poc.appleitunestopsongs.pojo.SongsAttributes

@Dao
interface SongAttributeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongAttributesToTbl(songsAttributes: SongsAttributes)

    @Query("SELECT * FROM SongsAttributes")
    fun getAllToptwentySongsFromTbl():LiveData<List<SongsAttributes>>
}