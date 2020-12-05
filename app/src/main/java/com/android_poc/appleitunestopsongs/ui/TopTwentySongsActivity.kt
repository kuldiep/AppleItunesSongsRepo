package com.android_poc.appleitunestopsongs.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.android_poc.appleitunestopsongs.R
import com.android_poc.appleitunestopsongs.recyclerview.TopTwentySongsAdapter
import com.android_poc.appleitunestopsongs.viewmodels.SongListViewModel
import com.android_poc.appleitunestopsongs.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_top_twenty_songs.*

class TopTwentySongsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_twenty_songs)

        rvTopTwentySongList.layoutManager = LinearLayoutManager(this)
        rvTopTwentySongList.itemAnimator = DefaultItemAnimator()
        val topTwentySongsAdapter = TopTwentySongsAdapter(arrayListOf(), this)
        rvTopTwentySongList.adapter = topTwentySongsAdapter

        val songListViewModel = ViewModelProviders.of(this, ViewModelFactory()).get(SongListViewModel::class.java)
        songListViewModel.getTopTwentySongsFromApi()
        songListViewModel.getApiCallbackListenerValue().observe(this, {
            if (!it) {
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                return@observe
            }
        })
        songListViewModel.getTopTwentySongsFromRepo().observe(this, {
            if (it.isNotEmpty()) {
                topTwentySongsAdapter.songAttributeList = it
            }
        })
    }
}