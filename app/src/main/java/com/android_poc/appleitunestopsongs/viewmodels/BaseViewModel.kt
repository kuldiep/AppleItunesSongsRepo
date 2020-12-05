package com.android_poc.appleitunestopsongs.viewmodels

import androidx.lifecycle.ViewModel
import com.android_poc.appleitunestopsongs.repository.AppleItunesRepository

abstract class BaseViewModel : ViewModel() {
    private var repoObj : AppleItunesRepository = AppleItunesRepository.getAppleItunesRepositoryInstance()

    open fun getBusRouteInfoRepository(): AppleItunesRepository {
        return repoObj
    }
}