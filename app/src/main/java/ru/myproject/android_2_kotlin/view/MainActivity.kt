package ru.myproject.android_2_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.myproject.android_2_kotlin.R
import ru.myproject.android_2_kotlin.databinding.ActivityMainBinding
import ru.myproject.android_2_kotlin.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.ok.setOnClickListener(clickListener)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}