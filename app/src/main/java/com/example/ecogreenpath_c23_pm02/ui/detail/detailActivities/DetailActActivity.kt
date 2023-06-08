package com.example.ecogreenpath_c23_pm02.ui.detail.detailActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.response.AllActivityResponse
import com.example.ecogreenpath_c23_pm02.data.response.DataAllActivity
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.databinding.ActivityDetailActBinding
import com.example.ecogreenpath_c23_pm02.utility.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class DetailActActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityDetailActBinding
    private val viewModel by viewModels<DetailActivityViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val activityId = intent.getStringExtra(EXTRA_ID)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)

        binding.apply {
            viewModel.getDetailActivity(activityId.toString()).observe(this@DetailActActivity){result->
                when(result){
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        val detailResponse : AllActivityResponse = result.data
                        val detailDataList : List<DataAllActivity> = detailResponse.data
                        val detailData: DataAllActivity? = detailDataList.firstOrNull()
                        if (detailData != null){
                            val title : String = detailData.activity_name
                            val price = detailData.budget
                            val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                            format.currency= Currency.getInstance("IDR")
                            val formatAmount = format.format(price)
                            val location:String = detailData.activity_location
                            val desc:String = detailData.activity_desc

                            detailTitle.text = title
                            detailPrice.text = formatAmount
                            activitiesLocation.text = location
                            activitiesDesc.text = desc
                        }
                    }
                    is Result.Error -> {
                        showLoading(false)
                        message(result.error)
                    }
                }

            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Set a marker at a specific location
        val location = LatLng(-8.373152, 115.286779)
        googleMap.addMarker(MarkerOptions().position(location).title("Tegal Dukuh Camp, Taro-Bali"))

        val cameraPosition = CameraPosition.Builder().target(location).zoom(15f).build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressBar5.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object{
        const val EXTRA_ID = "EXTRA_ID"
    }
}