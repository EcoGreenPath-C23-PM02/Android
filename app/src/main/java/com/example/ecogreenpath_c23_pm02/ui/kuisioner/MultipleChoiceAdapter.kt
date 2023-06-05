package com.example.ecogreenpath_c23_pm02.ui.kuisioner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogreenpath_c23_pm02.R

class MultipleChoiceAdapter(private val items: List<String>) : RecyclerView.Adapter<MultipleChoiceAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_multiple_choice, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.radioButton.isChecked = position == selectedPosition
        holder.radioButton.text = item

        holder.radioButton.setOnClickListener {
            setSelectedPosition(position)
            onItemClickListener?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.onItemClickListener = listener
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)
    }
}
