package com.android_poc.appleitunestopsongs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_poc.appleitunestopsongs.pojo.SongsAttributes
import com.android_poc.appleitunestopsongs.utility.ApiCallRespListener

class SongListViewModel : BaseViewModel() {
    private var apiCallListenerLiveData : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun getTopTwentySongsFromRepo(): LiveData<List<SongsAttributes>> {
        return getBusRouteInfoRepository().getSongAttributesListFromDB()
    }

    fun getTopTwentySongsFromApi(){
        val apiCallRespListener: ApiCallRespListener = object : ApiCallRespListener {
            override fun isNetworkCallSuccesfull(flag: Boolean) {
                apiCallListenerLiveData.value = flag
            }
        }
        getBusRouteInfoRepository().fetchTopTwentySongsFromNetwork(apiCallRespListener)
    }

    fun getApiCallbackListenerValue():LiveData<Boolean>{
        return apiCallListenerLiveData
    }
}