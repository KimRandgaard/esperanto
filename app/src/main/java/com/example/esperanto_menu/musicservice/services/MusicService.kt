package com.example.esperanto_menu.musicservice.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
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

    private val songs: List<Int> = listOf(
        R.raw.spaceoddity
    )


    fun runAction(state: MusicState) {
        musicState = state
        when (state) {
            MusicState.PLAY -> startMusic()
            MusicState.STOP -> stopMusic()
        }
    }

    private fun initializeMediaPlayer() {
       // if (songs.isNotEmpty()) {
            musicMediaPlayer = MediaPlayer()
        musicMediaPlayer!!.setDataSource(song)
        musicMediaPlayer!!.prepare()

     //   }
    }

    fun setSong(songURL : String) {
        song = songURL
    }

    private fun startMusic() {
        initializeMediaPlayer()
        musicMediaPlayer?.start()
    }


    private fun stopMusic() {
        musicMediaPlayer?.run {
            stop()
            release()
        }
    }

}