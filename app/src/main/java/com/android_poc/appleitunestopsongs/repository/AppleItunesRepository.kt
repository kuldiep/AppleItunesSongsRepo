package com.android_poc.appleitunestopsongs.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.android_poc.appleitunestopsongs.AppleItunesApplication
import com.android_poc.appleitunestopsongs.database.DataManager
import com.android_poc.appleitunestopsongs.models.EntryItem
import com.android_poc.appleitunestopsongs.models.ItunesApiRespTO
import com.android_poc.appleitunestopsongs.networking.GetTopItunesCallApi
import com.android_poc.appleitunestopsongs.networking.ItunesApiRetrofitClient
import com.android_poc.appleitunestopsongs.pojo.SongMetaData
import com.android_poc.appleitunestopsongs.pojo.SongsAttributes
import com.android_poc.appleitunestopsongs.utility.ApiCallRespListener
import com.android_poc.appleitunestopsongs.utility.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppleItunesRepository private constructor() {
    private var dataManager: DataManager

    init {
        dataManager = DataManager(AppleItunesApplication.getApplicationInstance())
    }

    private object HOLDER {
        val INSTANCE = AppleItunesRepository()
    }

    companion object {
        fun getAppleItunesRepositoryInstance(): AppleItunesRepository {
            val instance: AppleItunesRepository by lazy { HOLDER.INSTANCE }
            return instance
        }
    }

    fun fetchTopTwentySongsFromNetwork( apiCallbackListener:ApiCallRespListener) {
        val getTopItunesCallApi = ItunesApiRetrofitClient.buildService(
                GetTopItunesCallApi::class.java)

        getTopItunesCallApi.getTopTwentySongs().enqueue(object : Callback<ItunesApiRespTO> {
            override fun onResponse(call: Call<ItunesApiRespTO>?, response: Response<ItunesApiRespTO>?) {
                if (response?.isSuccessful!!) {
                    iterateResponse(response.body().feed?.entry as List<EntryItem>)
                    apiCallbackListener.isNetworkCallSuccesfull(true)
                }
            }

            override fun onFailure(call: Call<ItunesApiRespTO>?, t: Throwable?) {
                apiCallbackListener.isNetworkCallSuccesfull(false)
            }

        })
    }

    fun iterateResponse(entryItemList: List<EntryItem>) {
        val songsAttributeList = arrayListOf<SongsAttributes>()
        val songMetaDataList = arrayListOf<SongMetaData>()
        for (entryItem in entryItemList) {
            val songsAttributes = SongsAttributes()
            songsAttributes.artistName = entryItem.imArtist?.label
            songsAttributes.price = entryItem.imPrice?.label
            songsAttributes.songTitle = entryItem.title?.label
            songsAttributes.songThumbnail = entryItem.imImage?.get(0)?.label
            for (songActualLink in entryItem.link!!) {
                val songMetaData = SongMetaData()
                if (songActualLink?.attributes?.href?.matches(".m4a".toRegex())!!) {
                    Log.d(AppConstants.LOG_TAG, "iterateResponse: matches the string")
                    songMetaData.songActualUrl = songActualLink.attributes.href
                    songMetaData.songName = entryItem.title?.label
                    songMetaDataList.add(songMetaData)
                }
            }
            songsAttributeList.add(songsAttributes)
        }
        insertSongAttributeListIntoDB(songsAttributeList)
        insertSongMetaDataListIntoDB(songMetaDataList)

    }

    fun insertSongAttributeListIntoDB(songAttributeList: List<SongsAttributes>) {
        dataManager.insertSongAttributesIntoDB(songAttributeList)
    }

    fun insertSongMetaDataListIntoDB(songMetaDataList: List<SongMetaData>) {
        dataManager.insertSongMetaDataIntoDb(songMetaDataList)
    }

    fun getSongAttributesListFromDB():LiveData<List<SongsAttributes>>{
        return dataManager.getSongAttribuesListFromDB()
    }

    fun getSongMetaDataFromDB(songName:String):LiveData<SongMetaData>{
        return dataManager.getSongMetaDataBySongNameFromDB(songName)
    }
}


