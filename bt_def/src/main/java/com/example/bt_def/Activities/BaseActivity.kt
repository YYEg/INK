package com.example.bt_def.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bt_def.Fragments.DeviceListFragment
import com.example.bt_def.R

class BaseActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, DeviceListFragment()).commit()
    }
}