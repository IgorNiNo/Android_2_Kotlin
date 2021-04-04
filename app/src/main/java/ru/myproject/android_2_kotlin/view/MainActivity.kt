package ru.myproject.android_2_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ru.myproject.android_2_kotlin.R
import ru.myproject.android_2_kotlin.databinding.ActivityMainBinding
import ru.myproject.android_2_kotlin.view.experiments.ContentProviderFragment
import ru.myproject.android_2_kotlin.view.googlemaps.GoogleMapsFragment
import ru.myproject.android_2_kotlin.view.history.HistoryFragment
import ru.myproject.android_2_kotlin.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, HistoryFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }

            R.id.menu_content_provider -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, ContentProviderFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }

            R.id.menu_google_maps -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, GoogleMapsFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}