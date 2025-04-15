package com.example.pert7no1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FollowersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_followers)

        supportActionBar?.title = "Followers"
    }
}