package com.arabam.android_assignment.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android_assignment.adapters.AdvertListAdapter
import com.arabam.android_assignment.databinding.FragmentListBinding
import com.arabam.android_assignment.viewmodels.ListViewModel
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton


class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val listAdapter = AdvertListAdapter(arrayListOf())
    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)


        _binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            visibility = View.VISIBLE
        }

        viewModel.getAdvertList()

        _binding?.refreshLayout?.setOnRefreshListener {
            _binding?.errorView?.visibility = View.GONE
            _binding?.recyclerView?.loadSkeleton()
            viewModel.getDataFromAPI()
            _binding?.refreshLayout?.isRefreshing = false
        }



        listenData()

        /*    fragment_button.setOnClickListener{
                val action = ListFragmentDirections.actionListFragmentToDetailsFragment()
                Navigation.findNavController(it).navigate(action)
            }*/


    }

    private fun listenData() {
        viewModel.advertList.observe(viewLifecycleOwner, Observer { adverts ->
            adverts?.let {
                listAdapter.updateList(it)
            }
        })

        viewModel.advertError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    _binding?.errorView?.visibility = View.VISIBLE
                    _binding?.recyclerView?.visibility = View.GONE
                } else {
                    _binding?.errorView?.visibility = View.GONE
                }
            }
        })

        viewModel.advertLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    _binding?.recyclerView?.loadSkeleton()
                    _binding?.errorView?.visibility = View.GONE
                } else {
                    _binding?.recyclerView?.hideSkeleton()
                }
            }
        })
    }


}