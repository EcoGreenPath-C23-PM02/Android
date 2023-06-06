package com.example.ecogreenpath_c23_pm02.ui.quest.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogreenpath_c23_pm02.data.response.QuestList
import com.example.ecogreenpath_c23_pm02.databinding.QuestItemRowBinding
import com.example.ecogreenpath_c23_pm02.ui.quest.questUpload.QuestUploadActivity

class QuestAdapter : RecyclerView.Adapter<QuestAdapter.ViewHolder>() {

    private val questList = ArrayList<QuestList>()
    private var onItemClickCallback : OnItemClickCallback? = null

    fun setQuestList(questResponse: List<QuestList>){
        questList.clear()
        questList.addAll(questResponse)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: QuestItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(questResponse: QuestList) {
            with(binding) {
                questTitle.text = questResponse.quest
                questPoints.text = questResponse.points.toString()
                questLocation.text = questResponse.questLocation
                questDesc.text = questResponse.questDescription
            }

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, QuestUploadActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = QuestItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = questList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questList[position])
    }

    interface OnItemClickCallback{
        fun onItemClicked(questResponse: QuestList)
    }
}