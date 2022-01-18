package com.example.esperanto_menu.musicservice.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.esperanto_menu.R
import com.example.esperanto_menu.musicservice.model.MusicState


class MusicService : Service() {

    private val binder by lazy { MusicBinder() }

    override fun onBind(intent: Intent?): IBinder = binder

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private var musicState = MusicState.PAUSE
    private var musicMediaPlayer: MediaPlayer? = null

    private val songs: List<Int> = listOf(
        R.raw.spaceoddity
    )


    fun runAction(state: MusicState) {
        musicState = state
        when (state) {
            MusicState.PLAY -> startMusic()
            MusicState.STOP -> stopMusic()
            MusicState.PAUSE -> pauseMusic()
        }
    }

    private fun initializeMediaPlayer() {
        if (songs.isNotEmpty()) {
            musicMediaPlayer = MediaPlayer.create(this, songs.first()).apply {
                isLooping = true
            }
        }
    }

    private fun startMusic() {
       if(musicMediaPlayer == null)  {
           initializeMediaPlayer()
       }
        Log.d("MUSICPLAYER", "Start Position: " + musicMediaPlayer?.currentPosition)
        musicMediaPlayer?.start()
    }

    //Sørger for at man kan kun trykke på start knappen én gange for af afspille - ungå loop
    private fun stopMusic() {
        musicMediaPlayer?.stop()
    }
    private fun pauseMusic(){
        musicMediaPlayer?.pause()
        Log.d("MUSICPLAYER", "Pause Position: " + musicMediaPlayer?.currentPosition)
    }

}