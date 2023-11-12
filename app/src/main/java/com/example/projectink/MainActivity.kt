package com.example.projectink

import Fragments.SettingsFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val settingsFragment = SettingsFragment()
        loadFragment(settingsFragment)
        //startActivity(Intent(this, BaseActivity::class.java))
    }

    private fun loadFragment(settingsFragment: SettingsFragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_container, settingsFragment)
        transaction.commit()
    }
}
