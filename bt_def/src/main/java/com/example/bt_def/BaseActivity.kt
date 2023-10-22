package com.example.bt_def

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BaseActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        //initRcView()
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, DeviceListFragment()).commit()
    }

    private fun initRcView(){
        val rcView = findViewById<RecyclerView>(R.id.rcViewPaired)
        rcView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter()
        rcView.adapter = adapter
        adapter.submitList(createDeviceList())
    }

    private fun createDeviceList(): List<ListItem>{
        var list = ArrayList<ListItem>()
        for (i in 0 until 5){
            list.add(
                ListItem(
                    "Device $i",
                    "66:66:66:66"
                )
            )
        }
        return list
    }
}