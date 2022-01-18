package com.example.esperanto_menu.musicservice.player

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.esperanto_menu.databinding.FragmentPlayerBinding
import com.example.esperanto_menu.musicservice.model.MusicState
import com.example.esperanto_menu.musicservice.services.MusicService
import com.example.esperanto_menu.musicservice.viewmodel.PlayerViewModel
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class PlayerFragment : Fragment() {
    private var _binding: FragmentPlayerBinding? = null

    private val binding: FragmentPlayerBinding by lazy {
        FragmentPlayerBinding.inflate(layoutInflater)
    }
    private val viewModel: EsperantoViewModel by lazy {
        ViewModelProvider(this)[EsperantoViewModel::class.java]
    }

    private val navigationArgs: PlayerFragmentArgs by navArgs()

    private var musicService: MusicService? = null

    private val boundServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder: MusicService.MusicBinder = service as MusicService.MusicBinder
            musicService = binder.getService()
            viewModel.isMusicServiceBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            musicService?.runAction(MusicState.STOP)
            musicService = null
            viewModel.isMusicServiceBound = false
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root


}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val episode = viewModel.getEpisode(navigationArgs.episodeName,requireContext())

        musicService!!.setSong(episode.mp3fajlo)
        binding.playPause.setOnClickListener {
            sendCommandToBoundService(MusicState.PLAY)
        }
//        binding.btnStopMusic.setOnClickListener {
//            sendCommandToBoundService(MusicState.STOP)
//        }
    }

    override fun onStart() {
        super.onStart()
        // bind to service if it isn't bound
        if (!viewModel.isMusicServiceBound) bindToMusicService()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindMusicService()
    }

    private fun bindToMusicService() {
        activity?.bindService(Intent(activity, MusicService::class.java), boundServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun unbindMusicService() {
        if (viewModel.isMusicServiceBound) {
            // stop the audio
            musicService?.runAction(MusicState.STOP)

            // disconnect the service and save state
            activity?.unbindService(boundServiceConnection)
            viewModel.isMusicServiceBound = false
        }
    }

    private fun sendCommandToBoundService(state: MusicState) {
        if (viewModel.isMusicServiceBound) {
            musicService?.runAction(state)
            enableButtons(state)
        }
    }

    private fun enableButtons(state: MusicState) {
        val songPlays = state == MusicState.PLAY
        with(binding) {
            playPause.isEnabled = !songPlays
           // btnStopMusic.isEnabled = songPlays
        }
    }

}