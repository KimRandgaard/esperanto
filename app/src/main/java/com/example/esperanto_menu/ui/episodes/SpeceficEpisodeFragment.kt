package com.example.esperanto_menu.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.databinding.SpeceficEpisodeBinding
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.util.getDuration
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class SpeceficEpisodeFragment: Fragment() {

    private var _binding: SpeceficEpisodeBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: EsperantoViewModel by viewModels()
    lateinit var recyclerview : RecyclerView
    private lateinit var list: ArrayList<Channel>
    private val navigationArgs: SpeceficEpisodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container:ViewGroup?,
        saveInstaceState: Bundle?
    ): View? {

        _binding = SpeceficEpisodeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val episode = viewmodel.getEpisode(navigationArgs.episodeName,requireContext())

        binding.episodeNameEpisode.text = navigationArgs.episodeName.capitalize()

        binding.apply {

            channelNameOnEpisode.text = episode.nomo
            episodeNameEpisode.text = episode.plennomo
            episodeDescriptionEpisode.text = episode.teksto
           // episodeLenghtEpisode.text = getDuration(episode.mp3fajlo)
            // might need another method to get leanght of episode!

                    }

        val action = SpeceficEpisodeFragmentDirections.actionNavigationSpeceficEpisodeToNavigationEpisodes(episode.nomo)

        binding.backButtonEpisode.setOnClickListener{
            findNavController().navigate(action)

        }
        binding.playEpisode.setOnClickListener{
            val action = SpeceficEpisodeFragmentDirections.actionNavigationSpeceficEpisodeToPlayer(episode.plennomo)
            findNavController().navigate(action)
        }

    }


}