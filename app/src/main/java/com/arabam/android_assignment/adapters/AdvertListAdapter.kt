package com.arabam.android_assignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android_assignment.databinding.ListItemBinding
import com.arabam.android_assignment.models.Advert
import com.arabam.android_assignment.views.ListFragmentDirections

class AdvertListAdapter(private val advertList: ArrayList<Advert>) :
    RecyclerView.Adapter<AdvertListAdapter.AdvertViewHolder>(), AdvertClickListener {


    class AdvertViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return AdvertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdvertViewHolder, position: Int) {
        holder.binding.advert = advertList[position]
        holder.binding.listener = this
    }

    override fun getItemViewType(position: Int): Int {
        return position;
    }

    override fun getItemCount(): Int {
        return advertList.size
    }

    fun updateList(newList: List<Advert>) {
        advertList.clear()
        advertList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onAdvertClick(view: View, id: Int?) {
        val action =
            ListFragmentDirections.actionListFragmentToDetailsFragment(id!!)
        Navigation.findNavController(view).navigate(action)

//        val titleView: TextView = view!!.findViewById(R.id.titleView)

    }



}