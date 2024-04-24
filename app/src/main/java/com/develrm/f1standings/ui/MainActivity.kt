package com.develrm.f1standings.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.develrm.f1standings.R
import com.develrm.f1standings.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView(binding)
    }

    private fun initView(binding: ActivityMainBinding) {
        binding.tabLayout.getTabAt(0)?.view?.setOnClickListener{
            supportFragmentManager.commit {
                replace(binding.fragmentContainerView.id, DriverFragment())
            }
        }
        binding.tabLayout.getTabAt(1)?.view?.setOnClickListener{
            supportFragmentManager.commit {
                replace(binding.fragmentContainerView.id, ConstructorFragment())
            }
        }
        supportFragmentManager.commit {
            replace(binding.fragmentContainerView.id, DriverFragment())
        }
    }
}