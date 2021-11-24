package com.example.esperanto_menu.ui.channel

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

        return root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        val imageId = intArrayOf(R.drawable.channel_a)
//        val channelName = arrayOf("Mozaiko")
//        val channelDescription = arrayOf("Det her er den bedst podcast i verdenen")
//        val hoert = arrayOf("ja/nej")
//
//        channelArrayList = ArrayList()
//
//        for( i in channelName.indices){
//
//            val channel = Channels_Data(channelName[i], channelDescription[i], hoert[i], imageId[i])
//            channelArrayList.add(channel)
//
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
