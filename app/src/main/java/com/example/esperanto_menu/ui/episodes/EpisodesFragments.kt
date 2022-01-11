package com.example.esperanto_menu.ui.episodes

import android.os.Bundle
import android.text.Layout
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.databinding.SpeceficChannelBinding
import com.example.esperanto_menu.model.adapter.Channel_Adapter
import com.example.esperanto_menu.model.adapter.Episode_Adapter
import com.example.esperanto_menu.model.adapter.ToString_Adapter_Channels
import com.example.esperanto_menu.model.data.Channel

class EpisodesFragments : Fragment() {

    private var _binding: SpeceficChannelBinding? = null
    private val binding get() = _binding!!
    private val episodesviewmodel: EpisodesViewModel by viewModels()
    lateinit var recyclerview : RecyclerView
    private lateinit var myAdapter : Episode_Adapter
    private lateinit var list: ArrayList<Channel>

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

        val episodeList = episodesviewmodel.getEpisodeList(requireContext())

        binding.recyclerViewEpisodes.layoutManager = LinearLayoutManager(requireContext())
        val episode = episodeList.groupBy{
            it.nomo
        }.map {
            it.key
        }
        val adapter = ToString_Adapter_Episodes(requireContext(), episode)
        }
    }

}