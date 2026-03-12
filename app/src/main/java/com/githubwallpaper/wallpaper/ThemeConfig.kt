package com.githubwallpaper.wallpaper

enum class AppTheme(val id: String) {
    CLASSIC("classic"),
    NEON("neon"),
    DARK("dark"),
    PURPLE("purple"),
    PREMIUM("premium"),
    PREMIUM_GREY("premium_grey"),
    PREMIUM_GREEN("premium_green")
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
                backgroundColor = 0xFF0A0A0A.toInt(), // Pitch black
                emptyColor = 0xFF212121.toInt(),  // Dark Grey
                level1Color = 0xFF0E4429.toInt(), // Light green
                level2Color = 0xFF006D32.toInt(), // Med green
                level3Color = 0xFF26A641.toInt(), // Bright green
                level4Color = 0xFF39D353.toInt()  // Intense green
            )
        }
    }
}
