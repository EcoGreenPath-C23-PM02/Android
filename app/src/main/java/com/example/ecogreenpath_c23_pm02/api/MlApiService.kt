package com.example.ecogreenpath_c23_pm02.api

import com.example.ecogreenpath_c23_pm02.data.response.CbfResponse
import com.example.ecogreenpath_c23_pm02.data.response.CfResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MlApiService {
    @GET("homepage/{id}/cbf_recommendation")
    suspend fun getCbfRecommendation(
        @Path("id") id:String
    ) : CbfResponse

    @GET("homepage/{id}/cf_recommendation")
    suspend fun getCfRecommendation(
        @Path("id") id: String
    ) : List<CfResponse>
}