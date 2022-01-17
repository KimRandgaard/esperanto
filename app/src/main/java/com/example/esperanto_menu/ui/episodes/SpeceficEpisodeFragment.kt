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
import com.example.esperanto_menu.databinding.SpeceficChannelBinding
import com.example.esperanto_menu.databinding.SpeceficEpisodeBinding
import com.example.esperanto_menu.model.adapter.Episode_Adapter
import com.example.esperanto_menu.model.adapter.Specefik_Episode_Adapter
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class SpeceficEpisodeFragment: Fragment() {

    private var _binding: SpeceficEpisodeBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: EsperantoViewModel by viewModels()
    lateinit var recyclerview : RecyclerView
    private lateinit var myAdapter : Specefik_Episode_Adapter
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

        val episode = viewmodel.getEpisodesByChannel(navigationArgs.episodeName,requireContext())

        binding.episodeNameEpisode.text = navigationArgs.episodeName.capitalize()

      //  val adapter = Specefik_Episode_Adapter(requireContext(),episode)


        binding.backButtonEpisode.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_specefic_Episode_to_navigation_episodes)
        }

    }


}