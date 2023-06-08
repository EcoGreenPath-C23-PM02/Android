package com.example.ecogreenpath_c23_pm02.ui.allTour.allPackage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogreenpath_c23_pm02.data.response.DataAllActivity
import com.example.ecogreenpath_c23_pm02.data.response.DataAllPackage
import com.example.ecogreenpath_c23_pm02.databinding.AllPackageItemRowBinding
import com.example.ecogreenpath_c23_pm02.ui.allTour.allActivity.Adapter.AllActivityAdapter
import com.example.ecogreenpath_c23_pm02.ui.detail.detailActivities.DetailActActivity
import com.example.ecogreenpath_c23_pm02.ui.detail.detailPacket.DetailPackageActivity
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class AllPackageAdapter : RecyclerView.Adapter<AllPackageAdapter.ViewHolder>(){

    private val packageList = ArrayList<DataAllPackage>()
    private var onItemClickCallback : OnItemClickCallback? = null

    fun setAllPackageList(packageResponse: List<DataAllPackage>){
        packageList.clear()
        packageList.addAll(packageResponse)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: AllPackageItemRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(allPackage: DataAllPackage){
            with(binding){
                packageTitle.text = allPackage.package_name
                packageLocation.text = allPackage.location

                val duration = allPackage.duration
                if (duration == 1){
                    packageDuration.text = "$duration /day"
                }else{
                    packageDuration.text = "$duration /days"
                }

                val price = allPackage.budget
                val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                format.currency= Currency.getInstance("IDR")
                val formatAmount = format.format(price)

                packagePrice.text = formatAmount
                packageBundle.text = allPackage.pack_bundle
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailPackageActivity::class.java)
                intent.putExtra(DetailPackageActivity.EXTRA_ID, allPackage.pack_id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllPackageAdapter.ViewHolder {
        val view = AllPackageItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllPackageAdapter.ViewHolder, position: Int) {
        holder.bind(packageList[position])
    }

    override fun getItemCount(): Int = packageList.size

    interface OnItemClickCallback{
        fun onItemClicked(packageResponse: DataAllPackage)
    }

}