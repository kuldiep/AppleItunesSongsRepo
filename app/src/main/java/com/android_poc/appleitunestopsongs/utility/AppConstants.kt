package com.android_poc.appleitunestopsongs.utility

class AppConstants {
    companion object{
        const val BASE_URL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/"
        const val END_POINT = "ws/RSS/topsongs/limit=20/json"
        const val LOG_TAG = "LOG"
        const val FIRST_TIME_INSERTION_SONG_ATTRIBUTE_TBL = "FIRST_TIME_INSERTION_SONG_ATTRIBUTE_TBL"
        const val FIRST_TIME_INSERTION_SONG_META_DATA_TBL = "FIRST_TIME_INSERTION_SONG_META_DATA_TBL"
    }
}