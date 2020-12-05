package com.android_poc.appleitunestopsongs.pojo;

import org.jetbrains.annotations.NotNull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SongMetaData")
public class SongMetaData {
    @PrimaryKey
    @NotNull
    private String songName;
    private String songActualUrl;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongActualUrl() {
        return songActualUrl;
    }

    public void setSongActualUrl(String songActualUrl) {
        this.songActualUrl = songActualUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SongMetaData{");
        sb.append("songName='").append(songName).append('\'');
        sb.append(", songActualUrl='").append(songActualUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
