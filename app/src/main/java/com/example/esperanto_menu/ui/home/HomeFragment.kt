package com.example.esperanto_menu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esperanto_menu.databinding.FragmentHomeBinding
import com.example.esperanto_menu.model.adapter.Home_Adapter
import com.example.esperanto_menu.model.data.Channel
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: EsperantoViewModel by viewModels()
    lateinit var recycler: RecyclerView
    private lateinit var myAdapater: Home_Adapter
    private lateinit var list: ArrayList<Channel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val channelList = viewmodel.getchannellist(requireContext())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val channels = channelList.groupBy {
            it.nomo
        }.map {
            it.key
        }
        val MyAdapter = Home_Adapter(channelList.subList(0,5))
        binding.recyclerView.adapter = MyAdapter

        channels.forEach {
        }

    }

}