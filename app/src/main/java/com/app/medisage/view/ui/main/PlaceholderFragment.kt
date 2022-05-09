package com.app.medisage.view.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.medisage.viewmodel.MainViewModel
import com.app.medisage.MyViewModelFactory
import com.app.medisage.databinding.FragmentMainBinding
import com.app.medisage.model.ItemModel
import com.app.medisage.view.ui.MainAdapter
import com.app.medisage.data.repository.MainRepository
import com.app.medisage.data.repository.RetrofitService
import com.app.medisage.utils.CellClickListener


class PlaceholderFragment : Fragment(), CellClickListener {

    private var _binding: FragmentMainBinding? = null
    lateinit var viewModel: MainViewModel
    var favList: ArrayList<ItemModel> = ArrayList()
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter(this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        viewModel = ViewModelProvider(
            requireActivity(),
            MyViewModelFactory(MainRepository(retrofitService))
        ).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter
        if (arguments?.getInt(ARG_SECTION_NUMBER) == 1) {
            viewModel.postList.observe(requireActivity(), Observer {
                Log.d("TAG", "onCreate: $it")
                adapter.setItemList(it)
            })
        } else {
            viewModel.favList.postValue(favList)
            viewModel.favList.observe(requireActivity(), Observer {
                Log.d("TAG", "onCreate: $it")
                adapter.setItemList(it)
            })
        }
        //Load Post data only if post tab is selected
        if (arguments?.getInt(ARG_SECTION_NUMBER) == 1)
            viewModel.getAllPostList()
        return root
    }

    //item click call back listener
    override fun onCellClickListener(item: ItemModel) {
        if (arguments?.getInt(ARG_SECTION_NUMBER) == 1) {
            if (!favList.contains(item)) {
                favList.add(item)
                Toast.makeText(activity, "item added to fav", Toast.LENGTH_SHORT).show()
            } else {
                favList.remove(item)
                Toast.makeText(activity, "item removed from fav", Toast.LENGTH_SHORT).show()
            }
            viewModel.favList.value = favList
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}