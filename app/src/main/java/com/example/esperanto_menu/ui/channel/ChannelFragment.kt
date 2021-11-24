package com.example.esperanto_menu.ui.channel

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.esperanto_menu.R
import com.example.esperanto_menu.databinding.FragmentChannelBinding
import androidx.lifecycle.viewModelScope
import com.example.esperanto_menu.ui.network.ImportApi
import kotlinx.coroutines.launch

class ChannelFragment : Fragment() {

    private lateinit var channelViewModel: ChannelViewModel
    private var _binding: FragmentChannelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        channelViewModel =
            ViewModelProvider(this).get(ChannelViewModel::class.java)

        _binding = FragmentChannelBinding.inflate(inflater, container, false)
        val root: View = binding.root
        println("Hej Verden XXXXX")
        channelViewModel.viewModelScope.launch {
            try {
                println("Hej Verden XXXXX2")
                val listResult = ImportApi.retrofitService.getRadio()
                println("Hej Verden XXXXX3" + listResult)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageId = intArrayOf(R.drawable.channel_a)
        val channelName = arrayOf("Mozaiko")
        val channelDescription = arrayOf("Det her er den bedst podcast i verdenen")
        val hoert = arrayOf("ja/nej")


        var channelArrayList = ArrayList<Channels_Data>()
        channelArrayList.add(Channels_Data("Mozaiko","Mo","f","g","33","fefe"))
        channelArrayList.add(Channels_Data("p3","Mo","f","g","33","fefe"))
        channelArrayList.add(Channels_Data("p4","Mo","f","g","33","fefe"))
        channelArrayList.add(Channels_Data("p5","Mo","f","g","33","fefe"))

        binding.lvChannelList.setBackgroundColor(Color.CYAN)
        binding.lvChannelList.adapter = Channel_Adapter(requireContext(), channelArrayList)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
