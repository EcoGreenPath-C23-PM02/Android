package com.example.ecogreenpath_c23_pm02.ui.allTour.allActivity.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogreenpath_c23_pm02.data.response.DataAllActivity
import com.example.ecogreenpath_c23_pm02.data.response.QuestList
import com.example.ecogreenpath_c23_pm02.databinding.ActivitiesItemRowBinding
import com.example.ecogreenpath_c23_pm02.databinding.AllActivityItemRowBinding
import com.example.ecogreenpath_c23_pm02.ui.detail.detailActivities.DetailActActivity
import com.example.ecogreenpath_c23_pm02.ui.quest.adapter.QuestAdapter
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class AllActivityAdapter : RecyclerView.Adapter<AllActivityAdapter.ViewHolder>(){

    private val activityList = ArrayList<DataAllActivity>()
    private var onItemClickCallback : OnItemClickCallback? = null

    fun setActivitiesList(activitiesResponse: List<DataAllActivity>){
        activityList.clear()
        activityList.addAll(activitiesResponse)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: AllActivityItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(activity: DataAllActivity){
            with(binding){
                activitiesTitle.text = activity.activity_name
                activitiesLocation.text = activity.activity_location

                val price = activity.budget
                val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                format.currency= Currency.getInstance("IDR")
                val formatAmount = format.format(price)

                activityPrice.text = formatAmount
                activitiesDesc.text = activity.activity_desc
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActActivity::class.java)
                intent.putExtra(DetailActActivity.EXTRA_ID, activity.activity_id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllActivityAdapter.ViewHolder {
        val view = AllActivityItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllActivityAdapter.ViewHolder, position: Int) {
        holder.bind(activityList[position])
    }

    override fun getItemCount(): Int = activityList.size

    interface OnItemClickCallback{
        fun onItemClicked(activitiesResponse: DataAllActivity)
    }

}