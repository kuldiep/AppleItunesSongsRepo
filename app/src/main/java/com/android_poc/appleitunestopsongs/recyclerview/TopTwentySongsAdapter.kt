package com.android_poc.appleitunestopsongs.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android_poc.appleitunestopsongs.R
import com.android_poc.appleitunestopsongs.pojo.SongsAttributes
import com.android_poc.appleitunestopsongs.viewmodels.SongListViewModel
import com.squareup.picasso.Picasso

public class TopTwentySongsAdapter(var songAttributeList: List<SongsAttributes>,val context: Context) :
        RecyclerView.Adapter<TopTwentySongsAdapter.SongsListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_attribute_item, parent, false
        )
        return SongsListViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: SongsListViewHolder, position: Int) {
        if(songAttributeList.get(position).songThumbnail!=null){
            Picasso.get().load(songAttributeList.get(position).songThumbnail).into(holder.songThumbnail)
        }
        if(songAttributeList.get(position).songTitle!=null){
            holder.songTitle.text = songAttributeList.get(position).songTitle
        }
    }

    override fun getItemCount(): Int {
       return songAttributeList.size
    }

    @JvmName("setSongAttributeList1")
    fun setSongAttributeList(songAttributeList: List<SongsAttributes>){
        this.songAttributeList = songAttributeList
        notifyDataSetChanged()
    }

    class SongsListViewHolder(view:View,context: Context) : RecyclerView.ViewHolder(view){
        val songThumbnail = view.findViewById<ImageView>(R.id.ivSongThumbnail)
        val songTitle = view.findViewById<TextView>(R.id.tvSongTitle)
    }
}