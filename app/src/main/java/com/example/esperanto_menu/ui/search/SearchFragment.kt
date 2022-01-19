package com.example.esperanto_menu.ui.search

import android.nfc.Tag
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.esperanto_menu.databinding.FragmentSearchBinding
import com.example.esperanto_menu.ui.channel.ChannelFragmentDirections
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
            it.hejmo
            it.plennomo
            it.teksto
        }


        var searchArray = channelArray.toSet().toTypedArray()
        var resultArray = searchArray.toList()

        val searchAdapter : ArrayAdapter<String> = ArrayAdapter(
            requireActivity(),android.R.layout.simple_list_item_1,
            searchArray
        )



        binding.searchList.adapter = searchAdapter

        binding.searchList.setOnItemClickListener { parent, view, position, id ->
            val value = resultArray[position]
            val index = resultArray.indexOf(value)
            Log.d("SEARCH", "chanellist[index].nomo = " + channelList[index].nomo)

            val action = SearchFragmentDirections.actionNavigationSearchToNavigationEpisodes(channelList[index].nomo)

            Toast.makeText(requireContext(), "You clicked on + ${channelList[index].nomo}", Toast.LENGTH_LONG).show()


            findNavController().navigate(action)
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                binding.searchView.clearFocus()

                if (searchArray.contains(query)) {
                    searchAdapter.filter.filter(query)
                    //resultArray = searchArray.filter { s -> s == query }
                    //Toast.makeText(requireContext(), "Hej! Test ", Toast.LENGTH_SHORT).show()
                    Log.d("Tag", query.toString())
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchAdapter.filter.filter(newText)
                // resultArray = searchArray.filter { s -> s == newText }
                return false
            }

        })


        val root: View = binding.root


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
