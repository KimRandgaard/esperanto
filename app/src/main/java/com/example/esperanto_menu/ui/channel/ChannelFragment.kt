package com.example.esperanto_menu.ui.channel

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.esperanto_menu.R
import com.example.esperanto_menu.databinding.FragmentChannelBinding
import androidx.lifecycle.viewModelScope
import com.example.esperanto_menu.ui.network.ImportApi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import java.util.stream.DoubleStream.builder

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

        val channel = channelViewModel.getchannellist(requireContext())
    //    Log.d("Jens", channel.toString())

        channel.forEach{
            Log.d("Jens", it.nomo.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}