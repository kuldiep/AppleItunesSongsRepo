package com.android_poc.appleitunestopsongs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SongListViewModel::class.java)){
            return SongListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class");
    }
}