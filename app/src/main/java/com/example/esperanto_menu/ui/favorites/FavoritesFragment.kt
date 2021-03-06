package com.example.esperanto_menu.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.esperanto_menu.databinding.FragmentFavoritesBinding
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class FavoritesFragment : Fragment() {

    private val viewmodel: EsperantoViewModel by viewModels()
    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.favorites
        viewmodel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}