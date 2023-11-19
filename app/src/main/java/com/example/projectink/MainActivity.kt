package com.example.projectink

import Fragments.ConnectionFragment
import Fragments.HomeFragment
import Fragments.MessageFragment
import Fragments.SettingsFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectink.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHome -> replaceFragment(HomeFragment())
                R.id.navNotification -> replaceFragment(MessageFragment())
                R.id.navProfile -> replaceFragment(ConnectionFragment())
                R.id.navSearch -> replaceFragment(SettingsFragment())

                else -> {

                }

            }
            true
        }

        //startActivity(Intent(this, BaseActivity::class.java))
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, fragment)
        fragmentTransaction.commit()
    }
}
