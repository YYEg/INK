package com.example.projectink

import Fragments.ConnectionFragment
import Fragments.GroupFragment
import Fragments.MapFragment
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
        replaceFragment(MessageFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navMessage -> replaceFragment(MessageFragment())
                R.id.navGroup -> replaceFragment(GroupFragment())
                R.id.navMap -> replaceFragment(MapFragment())
                R.id.navConnection -> replaceFragment(ConnectionFragment())
                R.id.navSettings -> replaceFragment(SettingsFragment())

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
