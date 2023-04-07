package com.example.intents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.example.emptyactivity.model.Member
import com.example.emptyactivity.model.User

class SecondActivity : AppCompatActivity() {
    private lateinit var textView: AppCompatTextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initViews()
        loadData()
    }

    private fun initViews() {
        textView = findViewById(R.id.textSecond)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            backToMain(Member(5000.0, "Android"))
        }
    }

    private fun loadData() {
        val user = intent.getParcelableExtra<User>("user")
        textView.text = user.toString()
    }
    private fun backToMain(member: Member) {
        val intent = Intent()
        intent.putExtra("member", member)
        setResult(RESULT_OK, intent)
        finish()
    }

}