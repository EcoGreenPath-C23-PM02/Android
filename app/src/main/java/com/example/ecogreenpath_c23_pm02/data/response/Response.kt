package com.example.ecogreenpath_c23_pm02.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("message")
    val message: LoginResult
)

data class LoginResult(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("user_id")
    val user_id: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("token")
    val token: String
)

data class GeneralResponse(
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)

data class QuestUpload(
    val data: QuestUploadResponse
)


data class QuestUploadResponse(
    val id: String,
    val imageUrl: String,
    val userDesc: String
)

data class ProfileResponse(
    @SerializedName("data")
    val data: List<ProfileData>
)


data class QuestResponse(
    @SerializedName("data")
    val data: List<QuestList>
)


data class  QuestList(
    @SerializedName("quest_id")
    val questId: String,
    @SerializedName("quest")
    val quest: String,
    @SerializedName("poin")
    val points: Int,
    @SerializedName("quest_desc")
    val questDescription: String,
    @SerializedName("quest_location")
    val questLocation: String
)

data class ProfileData(
    @SerializedName("user_id")
    val userId: String,
    val username: String,
    val email: String,
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val birth: String,
    val point: Int?,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("profile_picture")
    val profilePicture: String?,
    @SerializedName("createdAt")
    val createdAt: String
)


data class MapsResponse(
    @SerializedName("data")
    val data: List<MapsData>
)

data class MapsData(
    @SerializedName("village_id")
    val villageId: String,
    @SerializedName("village_latitude")
    val villageLatitude: String,
    @SerializedName("village_longitude")
    val villageLongitude: String,
    val name: String,
    val description: String?,
    val province: String,
    val regency: String,
    val district: String,
    @SerializedName("social_media")
    val socialMedia: String?,
    val contact: String?,
    val picture: String?
)



data class CbfResponse(
    val recommendations : List<CbfData>
)

data class CbfData(
    val activity_id: Int,
    val activity_name: String,
    val avg_rating: Double,
    val budget: String
)


data class CfResponse(
    val budget: String,
    val pack_id: Int,
    val package_name: String,
    val user_rating: Double
)


data class AllActivityResponse(
    @SerializedName("data")
    val data: List<DataAllActivity>
)

//data class ProfileResponse(
//    @SerializedName("data")
//    val data: List<ProfileData>
//)

data class DataAllActivity(
    val activity_id: Int,
    val village_id: String?,
    val activity_location: String,
    val activity_name: String,
    val activity_category: String,
    val activity_level: String,
    val activity_desc: String,
    val budget: Int
)

data class AllPackageResponse(
    @SerializedName("data")
    val data: List<DataAllPackage>
)

data class DataAllPackage(
    val pack_id: Int,
    val village_id: String,
    val package_name: String,
    val duration: Int,
    val budget: Int,
    val pack_bundle: String,
    val location: String
)


data class PointResponse(
    @field:SerializedName("message")
    val message: String
)

data class QuesionerResponse(
    @SerializedName("responses")
    val responses: List<String>
)
