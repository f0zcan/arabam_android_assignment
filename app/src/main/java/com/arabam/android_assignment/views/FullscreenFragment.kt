package com.arabam.android_assignment.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.arabam.android_assignment.adapters.FullScreenSliderAdapter
import com.arabam.android_assignment.adapters.SliderAdapter
import com.arabam.android_assignment.databinding.FragmentFullscreenBinding
import com.arabam.android_assignment.util.downloadFromUrl


class FullscreenFragment : Fragment() {
    private var imageList: Array<String>? = null

    private var _binding: FragmentFullscreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFullscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            imageList = FullscreenFragmentArgs.fromBundle(it).imageList
        }


        _binding?.viewPager?.adapter =
            FullScreenSliderAdapter(_binding?.root?.context, ArrayList(imageList?.toList()))
        _binding?.closeButton?.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        (activity as AppCompatActivity).supportActionBar?.hide()



    }

}