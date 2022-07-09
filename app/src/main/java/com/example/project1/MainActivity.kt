package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button? = findViewById(R.id.mainbtn);
        button?.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java);
            startActivity(intent);
        }
    }
}