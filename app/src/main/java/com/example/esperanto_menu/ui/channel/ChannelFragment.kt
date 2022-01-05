package com.example.esperanto_menu.ui.channel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.esperanto_menu.databinding.FragmentChannelBinding

class ChannelFragment : Fragment() {

//    private lateinit var channelViewModel: ChannelViewModel
//    private var _binding: FragmentChannelBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!

    private var _binding: FragmentChannelBinding? = null
    private val binding get() = _binding!!
    private val channelviewmodel: ChannelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        channelviewmodel =
//            ViewModelProvider(this).get(ChannelViewModel::class.java)
//
//        _binding = FragmentChannelBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//        println("Hej Verden XXXXX")
//        channelviewmodel.viewModelScope.launch {
//            try {
//                println("Hej Verden XXXXX2")
//                val listResult = ImportApi.retrofitService.getRadio()
//                println("Hej Verden XXXXX3" + listResult)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        return root

        _binding = FragmentChannelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvChannellist.layoutManager


        val channel = channelviewmodel.getchannellist(requireContext())
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