//package com.example.esperanto_menu.player
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.esperanto_menu.R
//
//class PlayerFragment : Fragment() {
//
//    companion object {
//        fun newInstance() = PlayerFragment()
//    }
//
//    private lateinit var viewModel: PlayerViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.player_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}