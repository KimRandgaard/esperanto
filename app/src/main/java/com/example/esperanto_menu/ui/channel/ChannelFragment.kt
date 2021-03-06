package com.example.esperanto_menu.ui.channel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.databinding.FragmentChannelBinding
import com.example.esperanto_menu.model.adapter.Channel_Adapter
import com.example.esperanto_menu.model.adapter.Home_Adapter
import com.example.esperanto_menu.model.adapter.ToString_Adapter_Channels
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class ChannelFragment : Fragment() {

    private var _binding: FragmentChannelBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: EsperantoViewModel by viewModels()
    lateinit var recycler: RecyclerView
    private lateinit var myAdapater: Channel_Adapter
    private lateinit var list: ArrayList<Channel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChannelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val channelList = viewmodel.getchannellist(requireContext())
        //    Log.d("Jens", channel.toString())

        binding.recyclerViewChannels.layoutManager = LinearLayoutManager(requireContext())
        val channels = channelList.groupBy {
            it.nomo
        }.map {
            it.key
        }


        Log.d("dublicate", channels.toString())

        val adapter = ToString_Adapter_Channels(requireContext(), channels)
        binding.recyclerViewChannels.adapter = adapter
        channels.forEach {
            Log.d("Jens", it.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
