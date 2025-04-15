package com.example.pert7no1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val editTextTweet = findViewById<EditText>(R.id.editTextTweet)
        val buttonPost = findViewById<Button>(R.id.buttonPost)
        val btnClose = findViewById<TextView>(R.id.btnClose)

        buttonPost.setOnClickListener {
            val text = editTextTweet.text.toString()
            if (text.isNotBlank()) {
                Toast.makeText(this, "Tweet sent: $text", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Write something first!!", Toast.LENGTH_SHORT).show()
            }
        }

        btnClose.setOnClickListener {
            startActivity(Intent(this, AwalActivity::class.java))
            finish()
        }

        supportActionBar?.hide()
    }
}
