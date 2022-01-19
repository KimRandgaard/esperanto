package com.example.esperanto_menu.musicservice.player

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.esperanto_menu.databinding.FragmentPlayerBinding
import com.example.esperanto_menu.musicservice.model.MusicState
import com.example.esperanto_menu.musicservice.services.MusicService
import com.example.esperanto_menu.musicservice.viewmodel.PlayerViewModel
import com.example.esperanto_menu.ui.episodes.SpeceficEpisodeFragmentArgs
import com.example.esperanto_menu.ui.episodes.SpeceficEpisodeFragmentDirections
import com.example.esperanto_menu.viewModel.EsperantoViewModel
import java.lang.Exception
// Til musicservice er der hentet inspiration fra Ians lektion 3_2 omhandlende MusicService
// fra 3-ugers perioden. Denne omfatter hele musicService package herunder:
// MusicState, PlayerFragment, MusicService samt PlayerViewModel
// : https://learn.inside.dtu.dk/d2l/le/content/80550/viewContent/367175/View
class PlayerFragment : Fragment() {
    private var _binding: FragmentPlayerBinding? = null
    private val viewmodel: EsperantoViewModel by viewModels()
    private val navigationArgs: SpeceficEpisodeFragmentArgs by navArgs()
    private val binding get() = _binding!!
    private val viewModel: PlayerViewModel by lazy {
        ViewModelProvider(this)[PlayerViewModel::class.java]
    }

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
//        val episode = viewmodel.getEpisode(navigationArgs.episodeName,requireContext())


        val episode = viewmodel.getEpisode(navigationArgs.episodeName, navigationArgs.episodeDate, requireContext())

        binding.Play.setOnClickListener {
          sendCommandToBoundService(episode.mp3fajlo)
        }

        binding.apply {

            playerChannelName.text = episode.nomo.capitalize()
            playerEpisodeName.text = episode.plennomo.capitalize() + " " + episode.dato

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) musicService?.seekTo(progress)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })

        }


        binding.backButton.setOnClickListener {

           activity?.onBackPressed()
        }

        initSeekBar(binding.seekBar)
    }

    private fun initSeekBar(seekbar: SeekBar){
        seekbar.max = musicService!!.getDuration()

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run(){
                try{
                    seekbar.progress = musicService!!.getCurrentPos()
                    handler.postDelayed(this, 1000)
                } catch (e: Exception){
                    seekbar.progress = 0
                }
            }
        },0)
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

    private fun sendCommandToBoundService(mp3: String) {
        if (viewModel.isMusicServiceBound) {
            musicService?.setSong(mp3)
            var state = musicService?.getMusicState()
            var action = when(state){
                MusicState.PLAY -> MusicState.PAUSE
                else -> MusicState.PLAY
            }
            Log.d("STATE:", state.toString())
            Log.d("ACTION:", action.toString())
            musicService?.runAction(action, mp3)

        }
    }
}