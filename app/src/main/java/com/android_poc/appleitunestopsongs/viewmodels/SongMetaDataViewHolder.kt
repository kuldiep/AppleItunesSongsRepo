package com.android_poc.appleitunestopsongs.viewmodels

import androidx.lifecycle.LiveData
import com.android_poc.appleitunestopsongs.pojo.SongMetaData

class SongMetaDataViewHolder : BaseViewModel() {
    fun getSongMetaDataBySongNameFromRepo(songName:String):LiveData<SongMetaData>{
        return getBusRouteInfoRepository().getSongMetaDataFromDB(songName)
    }
}