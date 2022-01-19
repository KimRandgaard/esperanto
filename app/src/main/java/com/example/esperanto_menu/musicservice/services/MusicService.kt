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
// Til musicservice er der hentet inspiration fra Ians lektion 3_2 omhandlende MusicService
// fra 3-ugers perioden. Denne omfatter hele musicService package herunder:
// MusicState, PlayerFragment, MusicService samt PlayerViewModel
// : https://learn.inside.dtu.dk/d2l/le/content/80550/viewContent/367175/View

class MusicService : Service() {

    private lateinit var song : String
    private val binder by lazy { MusicBinder() }

    override fun onBind(intent: Intent?): IBinder = binder

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private var musicState = MusicState.PAUSE
    private var musicMediaPlayer: MediaPlayer? = null

    private val songs: MutableList<String> = mutableListOf()

    fun seekTo(progress: Int){
        musicMediaPlayer?.seekTo(progress)
    }

    fun getDuration(): Int{
        return musicMediaPlayer!!.duration
    }

    fun getCurrentPos(): Int{
        return musicMediaPlayer!!.currentPosition
    }

    fun getMusicState(): MusicState {
        return musicState
    }

    fun runAction(state: MusicState, songURL : String = "") {
        musicState = state
        when (state) {
            MusicState.PLAY -> startMusic(songURL)
            MusicState.STOP -> stopMusic()
            MusicState.PAUSE -> pauseMusic()
        }
    }

    // Der er til afspilleren fået inspiration fra android developer https://developer.android.com/guide/topics/media/mediaplayer#kotlin

    private fun initializeMediaPlayer(songURL : String) {
        //if (songs.isNotEmpty()) {
        // musicMediaPlayer = MediaPlayer()
        //musicMediaPlayer?.setDataSource("http://melburno.org.au/3ZZZradio/mp3/2021-11-22.3ZZZ.radio.mp3")
        //musicMediaPlayer?.prepare()

        var url = songURL //"http://melburno.org.au/3ZZZradio/mp3/2021-11-22.3ZZZ.radio.mp3"
        musicMediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                   AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                       .build()
                )
                setDataSource(url)

        }
        Log.d("Sang",songURL)
    }


    fun setSong(songURL : String) {
        songs.add(songURL)
        Log.d("Sang",songURL)
    }

    private fun startMusic(songURL : String) {
        if (musicMediaPlayer == null){
            initializeMediaPlayer(songURL)
            Log.d("MUSICPLAYER", "Staten: " + musicMediaPlayer?.toString())

            Log.d("MUSICPLAYER", "Start Position: " + musicMediaPlayer?.currentPosition)
            musicMediaPlayer?.prepare()
        }
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