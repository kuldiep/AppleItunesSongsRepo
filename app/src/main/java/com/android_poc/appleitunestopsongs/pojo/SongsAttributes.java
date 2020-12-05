package com.android_poc.appleitunestopsongs.pojo;

import org.jetbrains.annotations.NotNull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SongsAttributes")
public class SongsAttributes {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id;
    private String artistName;
    private String songTitle;
    private String songThumbnail;
    private String price;
    private String songName;

    public int getId() {
        return id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongThumbnail() {
        return songThumbnail;
    }

    public void setSongThumbnail(String songThumbnail) {
        this.songThumbnail = songThumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SongsAttributes{");
        sb.append("artistName='").append(artistName).append('\'');
        sb.append(", songTitle='").append(songTitle).append('\'');
        sb.append(", songUrl='").append(songThumbnail).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
