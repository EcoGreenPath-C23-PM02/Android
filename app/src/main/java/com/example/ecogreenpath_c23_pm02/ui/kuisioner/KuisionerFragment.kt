package com.example.ecogreenpath_c23_pm02.ui.kuisioner

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogreenpath_c23_pm02.R

class KuisionerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MultipleChoiceAdapter
    internal var isAnswerSelected = false
    private var listener: OnAnswerSelectedListener? = null

    interface OnAnswerSelectedListener {
        fun onAnswerSelected(isSelected: Boolean)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_kuisioner, container, false)
        recyclerView = view.findViewById(R.id.rv_multiple_choice)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val items = listOf("Tracking", "Cycling", "Conservation", "Cooking", "Packet Tour")
        adapter = MultipleChoiceAdapter(items)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener { position ->
            isAnswerSelected = true
            listener?.onAnswerSelected(isAnswerSelected)
            adapter.setSelectedPosition(position)
        }

        return view
    }

    fun setOnAnswerSelectedListener(listener: OnAnswerSelectedListener) {
        this.listener = listener
    }
}
