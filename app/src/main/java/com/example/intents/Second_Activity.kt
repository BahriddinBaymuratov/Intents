package com.example.intents

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import java.lang.reflect.Member

class SecondActivity : AppCompatActivity() {
    private lateinit var textView: AppCompatTextView
    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        initView()
        loadData()
    }

    private fun initView() {
        textView = findViewById(R.id.textView)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            backToMain(Member(5000.0, 'Andorid'))
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