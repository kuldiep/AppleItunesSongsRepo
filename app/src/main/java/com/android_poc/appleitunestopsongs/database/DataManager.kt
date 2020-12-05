package com.android_poc.appleitunestopsongs.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.android_poc.appleitunestopsongs.pojo.SongMetaData
import com.android_poc.appleitunestopsongs.pojo.SongsAttributes
import com.android_poc.appleitunestopsongs.utility.AppConstants
import com.android_poc.appleitunestopsongs.utility.AppSharedPrefRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

public class DataManager(application:Application) {

    private var appleItunesDBHelper: AppleItunesDBHelper

    init {
        appleItunesDBHelper = AppleItunesDBHelper.getInstance(application)
    }

    fun insertSongAttributesIntoDB(songsAttributesList: List<SongsAttributes>) {

        Completable.fromAction {
            for (songsAttribues in songsAttributesList)
                appleItunesDBHelper.getSongAttributeDao().insertSongAttributesToTbl(songsAttribues)
        }.subscribeOn(Schedulers.io()).doOnComplete {
            AppSharedPrefRepository.getInstance().setBoolean(
                    AppConstants.FIRST_TIME_INSERTION_SONG_ATTRIBUTE_TBL,false)
        }.subscribe()

    }

    fun insertSongMetaDataIntoDb(songMetaDataList: List<SongMetaData>) {
        Completable.fromAction {
            for (songMetaData in songMetaDataList)
                appleItunesDBHelper.getSongMetaDataDao().insertSongMetaDataIntoTbl(songMetaData)
        }.subscribeOn(Schedulers.io()).doOnComplete {
            AppSharedPrefRepository.getInstance().setBoolean(
                    AppConstants.FIRST_TIME_INSERTION_SONG_META_DATA_TBL,false)
        }.subscribe()
    }

    fun getSongAttribuesListFromDB() : LiveData<List<SongsAttributes>> {
        return appleItunesDBHelper.getSongAttributeDao().getAllToptwentySongsFromTbl()
    }

    fun getSongMetaDataBySongNameFromDB(songName:String) : LiveData<SongMetaData> {
        return appleItunesDBHelper.getSongMetaDataDao().getSongMetaDataBySongName(songName)
    }


}