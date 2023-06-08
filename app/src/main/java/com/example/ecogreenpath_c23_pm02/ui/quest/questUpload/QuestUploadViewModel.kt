package com.example.ecogreenpath_c23_pm02.ui.quest.questUpload

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class QuestUploadViewModel(private val repository: AppRepository) : ViewModel() {
    fun questUpload(id:String, image:MultipartBody.Part, description:RequestBody) =
        repository.uploadQuest(id,image,description)


}