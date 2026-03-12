package com.githubwallpaper.wallpaper

import android.content.SharedPreferences
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import androidx.preference.PreferenceManager
import com.githubwallpaper.data.GithubRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class GitHubWallpaperService : WallpaperService() {

    @Inject
    lateinit var repository: GithubRepository

    override fun onCreateEngine(): Engine {
        return GitHubEngine()
    }

    inner class GitHubEngine : Engine(), SharedPreferences.OnSharedPreferenceChangeListener {
        private val renderer = WallpaperRenderer()
        private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        private lateinit var prefs: SharedPreferences
        private var isVisible = false

        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)
            prefs = PreferenceManager.getDefaultSharedPreferences(this@GitHubWallpaperService)
            prefs.registerOnSharedPreferenceChangeListener(this)
            updateThemeFromPrefs()

            scope.launch {
                repository.contributions.collect { data ->
                    renderer.updateData(data)
                    requestDraw()
                }
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            prefs.unregisterOnSharedPreferenceChangeListener(this)
            scope.cancel()
        }

        override fun onVisibilityChanged(visible: Boolean) {
            this.isVisible = visible
            if (visible) {
                requestDraw()
            }
        }

        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            super.onSurfaceChanged(holder, format, width, height)
            requestDraw()
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            if (key == "theme") {
                updateThemeFromPrefs()
                requestDraw()
            }
        }

        private fun updateThemeFromPrefs() {
            val themeStr = prefs.getString("theme", "dark") ?: "dark"
            val appTheme = AppTheme.values().find { it.id == themeStr } ?: AppTheme.DARK
            renderer.updateTheme(appTheme)
        }

        private fun requestDraw() {
            if (isVisible) {
                scope.launch { drawFrame() }
            }
        }

        private fun drawFrame() {
            val holder = surfaceHolder
            var canvas: android.graphics.Canvas? = null
            try {
                canvas = holder.lockCanvas()
                if (canvas != null) {
                    val width = canvas.width
                    val height = canvas.height
                    renderer.draw(canvas, width, height)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        holder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}
