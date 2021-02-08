package com.arabam.android_assignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.arabam.android_assignment.databinding.ImageViewBinding

class SliderAdapter(private val mContext: Context?, private val itemList: ArrayList<String>?) :
    PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(container.context)
        val binding = ImageViewBinding.inflate(layoutInflater!!, container, false)
        binding.imageUrl = itemList?.get(position)?.replace("{0}", "800x600") ?: ""
        try {
            container.addView(binding.root, position)
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
        return binding.root
    }

    override fun getCount(): Int {
        return itemList?.size ?: 0
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}