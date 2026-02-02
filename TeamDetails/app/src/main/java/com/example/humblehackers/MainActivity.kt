package com.example.humblehackers

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    val suhas = findViewById<ImageView>(R.id.imageView_suhas)
    val soundar = findViewById<ImageView>(R.id.imageView_soundar)
    val sethu = findViewById<ImageView>(R.id.imageView_sethu)

        suhas.setOnClickListener{
            val intent = Intent(this,SuhasActivity::class.java)
            startActivity(intent)
        }
        soundar.setOnClickListener{
            val intent = Intent(this,SoundarActivity::class.java)
            startActivity(intent)
        }
        sethu.setOnClickListener{
            val intent = Intent(this,SethuActivity::class.java)
            startActivity(intent)
        }
    }
}