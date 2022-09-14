package com.example.intents

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: AppCompatTextView
    private lateinit var btnGo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        btnGo = findViewById(R.id.btnGo)

        btnGo.setOnClickListener{
            openSecondActivity(User("Android", "Kotlin", "45"))
        }
    }
    private val detailLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {res ->
        if(res.resultCode == Activity.RESULT_OK){
            val data = res.data
            val text = data?.getParcelableExtra<Member>("member")
            textView.text = text.toString()
        }
    }
    private fun openSecondActivity(user: User){
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("user", user)
        detailLauncher.launch(intent)
    }
}