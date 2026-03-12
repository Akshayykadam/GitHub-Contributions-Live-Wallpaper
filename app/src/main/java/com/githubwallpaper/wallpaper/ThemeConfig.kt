package com.githubwallpaper.wallpaper

import java.util.Calendar

enum class AppTheme(val id: String) {
    CLASSIC("classic"),
    NEON("neon"),
    DARK("dark"),
    PURPLE("purple"),
    PREMIUM("premium"),
    PREMIUM_GREY("premium_grey"),
    PREMIUM_GREEN("premium_green"),
    PREMIUM_LIGHT("premium_light"),
    PREMIUM_GREY_LIGHT("premium_grey_light"),
    PREMIUM_GREEN_LIGHT("premium_green_light")
}

data class ThemeColors(
    val backgroundColor: Int,
    val emptyColor: Int,
    val level1Color: Int,
    val level2Color: Int,
    val level3Color: Int,
    val level4Color: Int
)

object ThemeManager {

    /**
     * Resolves the effective theme based on auto-switch preference and time of day.
     * Daytime = 7:00 AM to 6:59 PM → light variant
     * Nighttime = 7:00 PM to 6:59 AM → dark variant (original)
     */
    fun resolveTheme(baseThemeId: String, autoSwitch: Boolean): AppTheme {
        val base = AppTheme.entries.firstOrNull { it.id == baseThemeId } ?: AppTheme.PREMIUM

        if (!autoSwitch) return base

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val isDaytime = hour in 7..18 // 7 AM to 6:59 PM

        return if (isDaytime) {
            when (base) {
                AppTheme.PREMIUM -> AppTheme.PREMIUM_LIGHT
                AppTheme.PREMIUM_GREY -> AppTheme.PREMIUM_GREY_LIGHT
                AppTheme.PREMIUM_GREEN -> AppTheme.PREMIUM_GREEN_LIGHT
                else -> base
            }
        } else {
            base
        }
    }

    fun getThemeColors(theme: AppTheme): ThemeColors {
        return when (theme) {
            AppTheme.CLASSIC -> ThemeColors(
                backgroundColor = 0xFFFFFFFF.toInt(),
                emptyColor = 0xFFEBEDF0.toInt(),
                level1Color = 0xFF9BE9A8.toInt(),
                level2Color = 0xFF40C463.toInt(),
                level3Color = 0xFF30A14E.toInt(),
                level4Color = 0xFF216E39.toInt()
            )
            AppTheme.NEON -> ThemeColors(
                backgroundColor = 0xFF000000.toInt(),
                emptyColor = 0xFF111111.toInt(),
                level1Color = 0xFF005500.toInt(),
                level2Color = 0xFF009900.toInt(),
                level3Color = 0xFF00DD00.toInt(),
                level4Color = 0xFF00FF00.toInt()
            )
            AppTheme.DARK -> ThemeColors(
                backgroundColor = 0xFF0D1117.toInt(),
                emptyColor = 0xFF161B22.toInt(),
                level1Color = 0xFF0E4429.toInt(),
                level2Color = 0xFF006D32.toInt(),
                level3Color = 0xFF26A641.toInt(),
                level4Color = 0xFF39D353.toInt()
            )
            AppTheme.PURPLE -> ThemeColors(
                backgroundColor = 0xFF120024.toInt(),
                emptyColor = 0xFF21004A.toInt(),
                level1Color = 0xFF651FFF.toInt(),
                level2Color = 0xFF7C4DFF.toInt(),
                level3Color = 0xFFB388FF.toInt(),
                level4Color = 0xFFD1C4E9.toInt()
            )
            // --- Dark Premium Themes ---
            AppTheme.PREMIUM -> ThemeColors(
                backgroundColor = 0xFF111111.toInt(),
                emptyColor = 0xFF2A2A2A.toInt(),
                level1Color = 0xFFE28B55.toInt(),
                level2Color = 0xFFE56A21.toInt(),
                level3Color = 0xFFF75C03.toInt(),
                level4Color = 0xFFFF4000.toInt()
            )
            AppTheme.PREMIUM_GREY -> ThemeColors(
                backgroundColor = 0xFF0A0A0A.toInt(),
                emptyColor = 0xFF1F1F1F.toInt(),
                level1Color = 0xFF444444.toInt(),
                level2Color = 0xFF777777.toInt(),
                level3Color = 0xFFAAAAAA.toInt(),
                level4Color = 0xFFFFFFFF.toInt()
            )
            AppTheme.PREMIUM_GREEN -> ThemeColors(
                backgroundColor = 0xFF0A0A0A.toInt(),
                emptyColor = 0xFF212121.toInt(),
                level1Color = 0xFF0E4429.toInt(),
                level2Color = 0xFF006D32.toInt(),
                level3Color = 0xFF26A641.toInt(),
                level4Color = 0xFF39D353.toInt()
            )
            // --- Light Premium Themes (used during daytime auto-switch) ---
            AppTheme.PREMIUM_LIGHT -> ThemeColors(
                backgroundColor = 0xFFF5F0EB.toInt(), // Warm off-white
                emptyColor = 0xFFE0D8CF.toInt(),      // Light tan
                level1Color = 0xFFE8A87C.toInt(),      // Soft peach
                level2Color = 0xFFE07B3C.toInt(),      // Warm orange
                level3Color = 0xFFD45A0A.toInt(),      // Rich orange
                level4Color = 0xFFBF3600.toInt()       // Deep burnt orange
            )
            AppTheme.PREMIUM_GREY_LIGHT -> ThemeColors(
                backgroundColor = 0xFFF2F2F2.toInt(), // Clean white-grey
                emptyColor = 0xFFDCDCDC.toInt(),      // Light silver
                level1Color = 0xFFAAAAAA.toInt(),      // Mid grey
                level2Color = 0xFF777777.toInt(),      // Dark grey
                level3Color = 0xFF444444.toInt(),      // Charcoal
                level4Color = 0xFF1A1A1A.toInt()       // Near black
            )
            AppTheme.PREMIUM_GREEN_LIGHT -> ThemeColors(
                backgroundColor = 0xFFF0F6F0.toInt(), // Mint white
                emptyColor = 0xFFD4E8D4.toInt(),      // Pale sage
                level1Color = 0xFF9BE9A8.toInt(),      // Light green
                level2Color = 0xFF40C463.toInt(),      // GitHub green
                level3Color = 0xFF30A14E.toInt(),      // Forest green
                level4Color = 0xFF216E39.toInt()       // Deep green
            )
        }
    }
}
