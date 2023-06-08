package com.example.ecogreenpath_c23_pm02.data.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecogreenpath_c23_pm02.api.MlApiService

class MlAppRepository(
    private val mlApiService: MlApiService
) {
    companion object{
        private var instance: MlAppRepository? = null

        fun getInstance(
            mlApiService: MlApiService
        ): MlAppRepository = instance ?: synchronized(this){
            instance ?: MlAppRepository(mlApiService)
        }
    }


    fun getCbfList(id:String) : LiveData<Result<List<CbfData>>> = liveData {
        emit(Result.Loading)
        try {
            val response = mlApiService.getCbfRecommendation(id)
            val cbfResponse = response.recommendations
            emit(Result.Success(cbfResponse))
        }catch (e:Exception){
            Log.d("cbf", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }

    fun getCfList(id:String) : LiveData<Result<List<CfResponse>>> = liveData {
        emit(Result.Loading)
        try {
            val response = mlApiService.getCfRecommendation(id)
            emit(Result.Success(response))
        }catch (e:Exception){
            Log.d("cf", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }
}