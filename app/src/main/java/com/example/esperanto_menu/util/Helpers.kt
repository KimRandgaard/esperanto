package com.example.esperanto_menu.util

import android.media.MediaMetadataRetriever
import android.util.Log

fun getDuration(url : String): String{


    var metaRetriever : MediaMetadataRetriever = MediaMetadataRetriever()
    metaRetriever.setDataSource(url)

    var out : String = ""
    var txtTime : String = ""

    var duration : String? = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)

    Log.d("Duration", duration.toString())

    var dur : Long = duration?.toLong() ?: 0
    var seconds : String = ((dur % 60000)/1000).toString()

    Log.d("SECONDS VALUE", seconds)

    var minutes:String = (dur / 60000).toString()
    out = minutes + ":" + seconds

    if (seconds.length == 1){
        txtTime = "0" + minutes + ":0" + seconds
    }
    else {
        txtTime = "0" + minutes + ":" + seconds
    }

    Log.d("MINUTES VALUE", minutes)
    Log.d("FORMATTED TIME", txtTime)

    metaRetriever.release()


    return minutes +" : "+ seconds

}