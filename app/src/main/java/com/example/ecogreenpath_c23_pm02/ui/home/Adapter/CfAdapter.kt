package com.example.ecogreenpath_c23_pm02.ui.home.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogreenpath_c23_pm02.data.response.CbfData
import com.example.ecogreenpath_c23_pm02.data.response.CfResponse
import com.example.ecogreenpath_c23_pm02.databinding.ActivitiesItemRowBinding
import com.example.ecogreenpath_c23_pm02.ui.detail.detailPacket.DetailPackageActivity

class CfAdapter : RecyclerView.Adapter<CfAdapter.ViewHolder>() {

    private val cfList = ArrayList<CfResponse>()
    private var onItemClickCallback : CfAdapter.OnItemClickCallback? = null


    fun setCfList(cfResponse: List<CfResponse>){
        cfList.clear()
        cfList.addAll(cfResponse)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ActivitiesItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(cfResponse: CfResponse){
            with(binding){
                activityItem.text = cfResponse.package_name
                activityPrice.text = cfResponse.budget
                rating.text = cfResponse.user_rating.toString()
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailPackageActivity::class.java)
                intent.putExtra(DetailPackageActivity.EXTRA_ID, cfResponse.pack_id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }



    interface OnItemClickCallback{
        fun onItemClicked(cbfResponse: CbfData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CfAdapter.ViewHolder {
        val view =  ActivitiesItemRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CfAdapter.ViewHolder, position: Int) {
        holder.bind(cfList[position])
    }

    override fun getItemCount(): Int = cfList.size
}