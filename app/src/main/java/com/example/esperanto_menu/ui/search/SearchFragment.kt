package com.example.esperanto_menu.ui.search

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.esperanto_menu.databinding.FragmentSearchBinding
import com.example.esperanto_menu.viewModel.EsperantoViewModel

class SearchFragment : Fragment() {

    private val viewmodel: EsperantoViewModel by activityViewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        // val testARrray = arrayOf("Kim","Borg","Jens","Aqib","Reza")

        val channelList = viewmodel.getchannellist(requireContext())
        val channelArray = channelList.map{
            it.nomo
            it.dato
            it.hejmo
            it.plennomo
            it.teksto
        }
        var searchArray = channelArray.toSet().toTypedArray()

        val searchAdapter : ArrayAdapter<String> = ArrayAdapter(
            requireActivity(),android.R.layout.simple_list_item_1,
            searchArray
        )



        binding.searchList.adapter = searchAdapter

        binding.searchList.setOnItemClickListener { parent, view, position, id ->
            val value = channelList[position]
            Toast.makeText(requireContext(), value.teksto, Toast.LENGTH_SHORT).show()
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if( searchArray != null) {

                }
                binding.searchView.clearFocus()

                if (searchArray.contains(query)) {
                    searchAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchAdapter.filter.filter(newText)
                return false
            }

        })


        val root: View = binding.root

/*
        val searchView: SearchView = binding.searchView
        viewmodel.text.observe(viewLifecycleOwner, Observer {
            // searchView.text = it
        })
*/

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}