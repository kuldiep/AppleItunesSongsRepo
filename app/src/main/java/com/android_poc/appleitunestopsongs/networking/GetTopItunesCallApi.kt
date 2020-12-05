package com.android_poc.appleitunestopsongs.networking

import com.android_poc.appleitunestopsongs.models.Feed
import com.android_poc.appleitunestopsongs.models.ItunesApiRespTO
import com.android_poc.appleitunestopsongs.utility.AppConstants
import retrofit2.Call
import retrofit2.http.GET

interface GetTopItunesCallApi {

    @GET(AppConstants.END_POINT)
    fun getTopTwentySongs(): Call<ItunesApiRespTO>
}