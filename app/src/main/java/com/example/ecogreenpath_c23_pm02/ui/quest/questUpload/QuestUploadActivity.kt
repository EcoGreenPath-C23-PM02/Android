package com.example.ecogreenpath_c23_pm02.ui.quest.questUpload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecogreenpath_c23_pm02.R
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.pref.UserSharedPreferences
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.data.response.pointInput
import com.example.ecogreenpath_c23_pm02.databinding.ActivityQuestUploadBinding
import com.example.ecogreenpath_c23_pm02.ui.MainActivity
import com.example.ecogreenpath_c23_pm02.ui.quest.QuestActivity
import com.example.ecogreenpath_c23_pm02.utility.ViewModelFactory
import com.example.ecogreenpath_c23_pm02.utility.gone
import com.example.ecogreenpath_c23_pm02.utility.show
import com.example.ecogreenpath_c23_pm02.utility.createTempFile
import com.example.ecogreenpath_c23_pm02.utility.reduceFileImage
import com.example.ecogreenpath_c23_pm02.utility.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class QuestUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestUploadBinding

    private lateinit var currentPhotoPath: String

    private val viewModel by viewModels<QuestUploadViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION){
            if (!allPermissionGranted()){
                Toast.makeText(
                    this,
                    "Don't Have Permission To Access",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = "Do The Quest!"
        }

        if(!allPermissionGranted()){
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSION,
                REQUEST_CODE_PERMISSION
            )
        }

        binding.buttonCamera.setOnClickListener {  startCamera() }
        binding.buttonGallery.setOnClickListener { startGallery() }
        binding.button.setOnClickListener { uploadQuestImage() }
    }



    private fun uploadQuestImage(){
        val description = binding.phoneNumberEditText.text.toString()

        if (getFile != null ){
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("image/jpg".toMediaType())
            val userId = UserSharedPreferences.getUserId(this)
            val imageMultipart: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", file.name, requestImageFile)
            uploadQuest(
                userId,
                imageMultipart,
                description.toRequestBody("text/plain".toMediaType())
            )
        }else{
            binding.button.isEnabled = true
            showMessage("Image Required")
        }
    }


    private fun uploadQuest(id: String, imageMultipart:MultipartBody.Part, toRequestBody: RequestBody){
        viewModel.questUpload(id,imageMultipart,toRequestBody).observe(this){result ->
            when(result){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    result.data.let {it ->

                        Intent(this@QuestUploadActivity, MainActivity::class.java).also {
                            startActivity(it)
                            finishAffinity()
                        }
                    }
                }
                is Result.Error -> {
                    showLoading(false)
                    showMessage(result.error)
                }
            }
        }
    }

    private fun startCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        createTempFile(application).also{
            val photoUri: Uri = FileProvider.getUriForFile(
                this@QuestUploadActivity,
                "com.example.ecogreenpath_c23_pm02",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            launcherIntentCamera.launch(intent)
        }
    }

    private var getFile: File? = null
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == RESULT_OK){
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding.imagePreview.setImageBitmap(result)
        }
    }

    private fun startGallery(){
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture to Upload")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == RESULT_OK){
                val selectedImage: Uri = result.data?.data as Uri
                val myFile = uriToFile(selectedImage, this@QuestUploadActivity)
                getFile = myFile
                binding.imagePreview.setImageURI(selectedImage)
            }
        }



    private fun showLoading(isLoading: Boolean){
        if (isLoading) binding.progressBar3.show() else binding.progressBar3.gone()
    }
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    companion object{
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSION = 10
    }
}