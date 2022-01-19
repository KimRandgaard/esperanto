package com.example.esperanto_menu.musicservice.services

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.esperanto_menu.R
import com.example.esperanto_menu.musicservice.model.MusicState
import java.net.URI


class MusicService : Service() {

    private lateinit var song : String
    private val binder by lazy { MusicBinder() }

    override fun onBind(intent: Intent?): IBinder = binder

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private var musicState = MusicState.STOP
    private var musicMediaPlayer: MediaPlayer? = null

    private val songs: MutableList<String> = mutableListOf()


    fun runAction(state: MusicState) {
        musicState = state
        when (state) {
            MusicState.PLAY -> startMusic()
            MusicState.STOP -> stopMusic()
            MusicState.PAUSE -> pauseMusic()
        }
    }

    private fun initializeMediaPlayer() {
        //if (songs.isNotEmpty()) {
        // musicMediaPlayer = MediaPlayer()
        //musicMediaPlayer?.setDataSource("http://melburno.org.au/3ZZZradio/mp3/2021-11-22.3ZZZ.radio.mp3")
        //musicMediaPlayer?.prepare()

        musicMediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                   AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                       .build()
                )
                setDataSource("http://melburno.org.au/3ZZZradio/mp3/2021-11-22.3ZZZ.radio.mp3")

        }
        //}
    }


    fun setSong(songURL : String) {
        songs.add(songURL)
        Log.d("Sang",songURL)
    }

    private fun startMusic() {
        initializeMediaPlayer()
        Log.d("MUSICPLAYER", "Staten: " + musicMediaPlayer?.toString())

        Log.d("MUSICPLAYER", "Start Position: " + musicMediaPlayer?.currentPosition)
        musicMediaPlayer?.prepare()
        musicMediaPlayer?.start()
    }

    //Sørger for at man kan kun trykke på start knappen én gange for af afspille - ungå loop
    private fun stopMusic() {
        musicMediaPlayer?.run {
            stop()
            release()
        }
    }

    private fun pauseMusic(){
    musicMediaPlayer?.pause()
    Log.d("MUSICPLAYER", "Pause Position: " + musicMediaPlayer?.currentPosition)
}

}