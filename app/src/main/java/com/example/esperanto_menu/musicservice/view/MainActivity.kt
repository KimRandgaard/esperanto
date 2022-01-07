package dtu.engtech.musicservice.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dtu.engtech.musicservice.R
import dtu.engtech.musicservice.databinding.ActivityMainBinding
import dtu.engtech.musicservice.model.MusicState
import dtu.engtech.musicservice.services.MusicService
import dtu.engtech.musicservice.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var musicService: MusicService? = null

    private val boundServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder: MusicService.MusicBinder = service as MusicService.MusicBinder
            musicService = binder.getService()
            mainViewModel.isMusicServiceBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            musicService?.runAction(MusicState.STOP)
            musicService = null
            mainViewModel.isMusicServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnPlayMusic.setOnClickListener {
            sendCommandToBoundService(MusicState.PLAY)
        }
        binding.btnStopMusic.setOnClickListener {
            sendCommandToBoundService(MusicState.STOP)
        }
    }

    override fun onStart() {
        super.onStart()
        // bind to service if it isn't bound
        if (!mainViewModel.isMusicServiceBound) bindToMusicService()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindMusicService()
    }

    private fun bindToMusicService() {
        bindService(Intent(this, MusicService::class.java), boundServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun unbindMusicService() {
        if (mainViewModel.isMusicServiceBound) {
            // stop the audio
            musicService?.runAction(MusicState.STOP)

            // disconnect the service and save state
            unbindService(boundServiceConnection)
            mainViewModel.isMusicServiceBound = false
        }
    }

    private fun sendCommandToBoundService(state: MusicState) {
        if (mainViewModel.isMusicServiceBound) {
            musicService?.runAction(state)
            enableButtons(state)
        }
    }

    private fun enableButtons(state: MusicState) {
        val songPlays = state == MusicState.PLAY
        with(binding) {
            btnPlayMusic.isEnabled = !songPlays
            btnStopMusic.isEnabled = songPlays
        }
    }
}