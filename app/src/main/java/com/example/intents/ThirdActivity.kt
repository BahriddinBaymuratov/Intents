package com.example.intents

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.graphics.drawable.toBitmap
import com.google.android.material.button.MaterialButton

class ThirdActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var editText: AppCompatEditText
    private lateinit var editTextCall: AppCompatEditText
    private lateinit var btnSend: Button
    private lateinit var btnGetImage: MaterialButton
    private lateinit var btnCall: MaterialButton
    private lateinit var share: MaterialButton

    private val REQUEST_CODE = 1001
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        initViews()
    }

    private fun initViews() {
        imageView = findViewById(R.id.image)
        editText = findViewById(R.id.appCompatEditText2)
        editTextCall = findViewById(R.id.editCall)
        btnSend = findViewById(R.id.btnSend)
        btnGetImage = findViewById(R.id.btnGetImage)
        btnCall = findViewById(R.id.btnCall)
        share = findViewById(R.id.Share)

        btnSend.setOnClickListener {
            shareText()
        }
        share.setOnClickListener {
            shareImage()
        }
        btnGetImage.setOnClickListener {
            getImageNew()
        }
        btnCall.setOnClickListener {
            call()
        }
    }

    // new way
    private fun getImageNew() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                imageUri = data?.data
                imageView.setImageURI(imageUri)
            }
        }

    // old way
    private fun getImageOld() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_OK && resultCode == REQUEST_CODE)
            imageUri = data?.data
        imageView.setImageURI(imageUri)
    }

    private fun call() {
        btnCall.setOnClickListener {
            val text = editTextCall.text.toString().trim()
            val intent = Intent()
            intent.action = Intent.ACTION_CALL
            intent.data = Uri.parse("tel:$text")
            startActivity(intent)
        }
    }

    private fun goToWebSite() {
        val text = editText.text.toString().trim()
        if (text.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com")
            startActivity(Intent.createChooser(intent, "bitmap"))
        }
    }

    private fun shareText() {
        btnSend.setOnClickListener {
            val text = editText.text.toString().trim()
            val intent = Intent(Intent.ACTION_SEND)
            intent.apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "$text")
            }
            startActivity(Intent.createChooser(intent, "Share"))
        }
    }

    private fun shareImage() {
        val myImage = imageView.drawable
        val bitmap = myImage.toBitmap(imageView.width, imageView.height)
        val path =
            MediaStore.Images.Media.insertImage(contentResolver, bitmap, "My image", String())
        val uri = Uri.parse(path)
        val intent = Intent()
        intent.apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/*"
        }
        startActivity(Intent.createChooser(intent, "test"))
    }

}