package com.example.esperanto_menu.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.esperanto_menu.R
import com.example.esperanto_menu.databinding.FragmentSearchBinding
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class SearchFragment : Fragment() {

    private val viewmodel: EsperantoViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchView: SearchView = binding.searchView
        viewmodel.text.observe(viewLifecycleOwner, Observer {
            // searchView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}