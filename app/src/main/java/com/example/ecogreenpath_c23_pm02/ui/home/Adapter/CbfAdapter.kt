package com.example.ecogreenpath_c23_pm02.ui.home.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogreenpath_c23_pm02.data.response.CbfData
import com.example.ecogreenpath_c23_pm02.data.response.QuestList
import com.example.ecogreenpath_c23_pm02.databinding.ActivitiesItemRowBinding
import com.example.ecogreenpath_c23_pm02.ui.detail.detailActivities.DetailActActivity
import com.example.ecogreenpath_c23_pm02.ui.quest.adapter.QuestAdapter

class CbfAdapter : RecyclerView.Adapter<CbfAdapter.ViewHolder>() {

    private val cbfList = ArrayList<CbfData>()
    private var onItemClickCallback : CbfAdapter.OnItemClickCallback? = null

    fun setCbfList(cbfResponse: List<CbfData>){
        cbfList.clear()
        cbfList.addAll(cbfResponse)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ActivitiesItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cbfResponse: CbfData){
            with(binding){
                activityItem.text = cbfResponse.activity_name
                activityPrice.text = cbfResponse.budget
                rating.text = cbfResponse.avg_rating.toString()
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActActivity::class.java)
                intent.putExtra(DetailActActivity.EXTRA_ID, cbfResponse.activity_id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }


    interface OnItemClickCallback{
        fun onItemClicked(cbfResponse: CbfData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CbfAdapter.ViewHolder {
        val view =  ActivitiesItemRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CbfAdapter.ViewHolder, position: Int) {
        holder.bind(cbfList[position])
    }

    override fun getItemCount(): Int = cbfList.size

}