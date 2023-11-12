package com.example.projectink

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.bt_def.Activities.BaseActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, BaseActivity::class.java))

    }
}