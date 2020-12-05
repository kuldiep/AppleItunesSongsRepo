package com.android_poc.appleitunestopsongs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android_poc.appleitunestopsongs.daos.SongAttributeDao
import com.android_poc.appleitunestopsongs.daos.SongMetaDataDao
import com.android_poc.appleitunestopsongs.pojo.SongMetaData
import com.android_poc.appleitunestopsongs.pojo.SongsAttributes

@Database(entities = arrayOf(SongsAttributes::class, SongMetaData::class), version = 1, exportSchema = false)
abstract class AppleItunesDBHelper : RoomDatabase() {

    abstract fun getSongAttributeDao(): SongAttributeDao
    abstract fun getSongMetaDataDao(): SongMetaDataDao

    companion object {
        private var mInstance: AppleItunesDBHelper? = null
        fun getInstance(context: Context): AppleItunesDBHelper {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                        context.applicationContext, AppleItunesDBHelper::class.java, "AppleItunesDBHelper"
                ).build()
            }
            return mInstance as AppleItunesDBHelper
        }
    }
}