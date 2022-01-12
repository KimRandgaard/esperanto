package com.example.esperanto_menu.ui.episodes

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.R
import com.example.esperanto_menu.databinding.SpeceficChannelBinding
import com.example.esperanto_menu.model.adapter.Episode_Adapter
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class EpisodesFragments : Fragment() {

    private var _binding: SpeceficChannelBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: EsperantoViewModel by viewModels()
    lateinit var recyclerview : RecyclerView
    private lateinit var myAdapter : Episode_Adapter
    private lateinit var list: ArrayList<Channel>
    private val navigationArgs: EpisodesFragmentsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstancesState: Bundle?
    ): View? {

        _binding = SpeceficChannelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val episodeList = viewmodel.getEpisodesByChannel(navigationArgs.channelName,requireContext())


        binding.recyclerViewEpisodes.layoutManager = LinearLayoutManager(requireContext())

        val adapter = Episode_Adapter(requireContext(), episodeList)
        binding.recyclerViewEpisodes.adapter = adapter

        binding.speceficChannelName.text = navigationArgs.channelName.capitalize()

//        binding.VundetTilStart.setOnClickListener {
//            findNavController().navigate(R.id.action_gameWon_to_startGameFragment)
//    }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_episodes_to_navigation_channels)
        }


        }


    }