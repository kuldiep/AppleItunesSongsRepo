package com.android_poc.appleitunestopsongs.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android_poc.appleitunestopsongs.pojo.SongMetaData

@Dao
interface SongMetaDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongMetaDataIntoTbl(songMetaData: SongMetaData)

    @Query("SELECT * FROM SongMetaData WHERE songName = :songName")
    fun getSongMetaDataBySongName(songName:String):LiveData<SongMetaData>
}