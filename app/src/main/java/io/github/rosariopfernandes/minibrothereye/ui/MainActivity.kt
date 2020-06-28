package io.github.rosariopfernandes.minibrothereye.ui

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.databinding.ActivityMainBinding
import io.github.rosariopfernandes.minibrothereye.util.PREF_DARK_THEME

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    private var darkModeEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.findItem(R.id.action_dark_theme_toggle)
        darkModeEnabled = preferences.getBoolean(PREF_DARK_THEME, false)
        if (darkModeEnabled) {
            menuItem.icon =
                ContextCompat.getDrawable(this,
                    R.drawable.ic_brightness_high_white_24dp
                )
            menuItem.title = getString(R.string.light_theme)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)
            }
        } else {
            menuItem.icon =
                ContextCompat.getDrawable(this,
                    R.drawable.ic_brightness_medium_white_24dp
                )
            menuItem.title = getString(R.string.dark_theme)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_dark_theme_toggle -> {
                darkModeEnabled = preferences.getBoolean(PREF_DARK_THEME, false)
                darkModeEnabled = !darkModeEnabled
                preferences.edit {
                    putBoolean(PREF_DARK_THEME, darkModeEnabled)
                }
                if (darkModeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
                recreate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}