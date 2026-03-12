package com.githubwallpaper.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.githubwallpaper.R
import com.githubwallpaper.data.GithubRepository
import com.githubwallpaper.worker.SyncScheduler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: GithubRepository

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val btnVerify = findViewById<Button>(R.id.btnVerify)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        // Pre-fill
        val savedUsername = prefs.getString("username", "")
        etUsername.setText(savedUsername)
        
        // Theme selection logic
        val rgTheme = findViewById<android.widget.RadioGroup>(R.id.rgTheme)
        val rbOrange = findViewById<android.widget.RadioButton>(R.id.rbOrange)
        val rbGrey = findViewById<android.widget.RadioButton>(R.id.rbGrey)
        val rbGreen = findViewById<android.widget.RadioButton>(R.id.rbGreen)

        val savedTheme = prefs.getString("theme", "premium")
        if (savedTheme == "premium_grey") {
            rbGrey.isChecked = true
        } else if (savedTheme == "premium_green") {
            rbGreen.isChecked = true
        } else {
            rbOrange.isChecked = true
        }

        rgTheme.setOnCheckedChangeListener { _, checkedId ->
            val selectedTheme = when (checkedId) {
                R.id.rbGrey -> "premium_grey"
                R.id.rbGreen -> "premium_green"
                else -> "premium"
            }
            prefs.edit().putString("theme", selectedTheme).apply()
            
            // Re-trigger sync to push update immediately to renderer via DB trigger
            val user = etUsername.text.toString().trim()
            if (user.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    try { repository.syncContributions(user) } catch (e: Exception) {}
                }
            }
        }

        // Auto Dark/Light Switch
        val switchAutoTheme = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.switchAutoTheme)
        switchAutoTheme.isChecked = prefs.getBoolean("auto_switch", false)
        switchAutoTheme.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("auto_switch", isChecked).apply()
        }

        btnVerify.setOnClickListener {
            val username = etUsername.text.toString().trim()
            if (username.isEmpty()) return@setOnClickListener

            // UI Loading state
            btnVerify.text = ""
            progressBar.visibility = View.VISIBLE
            tvStatus.text = ""

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    repository.syncContributions(username)
                    
                    // Save on success
                    prefs.edit().putString("username", username).apply()
                    SyncScheduler.schedulePeriodicSync(this@SettingsActivity)

                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        btnVerify.text = "Account Linked!"
                        tvStatus.text = "Wallpaper is ready. You can now apply it in your system settings."
                        tvStatus.setTextColor(android.graphics.Color.parseColor("#40C463"))
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        btnVerify.text = "Verify & Link Account"
                        tvStatus.text = "Failed to fetch contributions. Check username and network."
                        tvStatus.setTextColor(android.graphics.Color.parseColor("#FF4C4C"))
                    }
                }
            }
        }

        // Set Wallpaper button
        val btnSetWallpaper = findViewById<Button>(R.id.btnSetWallpaper)
        btnSetWallpaper.setOnClickListener {
            try {
                val intent = android.content.Intent(
                    android.app.WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER
                )
                intent.putExtra(
                    android.app.WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    android.content.ComponentName(
                        this,
                        com.githubwallpaper.wallpaper.GitHubWallpaperService::class.java
                    )
                )
                startActivity(intent)
            } catch (e: Exception) {
                // Fallback for devices that don't support ACTION_CHANGE_LIVE_WALLPAPER
                try {
                    val fallback = android.content.Intent(android.app.WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER)
                    startActivity(fallback)
                } catch (e2: Exception) {
                    tvStatus.text = "Could not open wallpaper picker. Please set manually."
                    tvStatus.setTextColor(android.graphics.Color.parseColor("#FF4C4C"))
                }
            }
        }
    }
}
