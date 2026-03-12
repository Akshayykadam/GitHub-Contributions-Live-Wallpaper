package com.githubwallpaper.wallpaper

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import com.githubwallpaper.data.ContributionDayEntity
import java.util.Calendar

class WallpaperRenderer {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val quotePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("monospace", Typeface.NORMAL)
        letterSpacing = 0.02f
    }

    private var contributions: List<ContributionDayEntity> = emptyList()
    private var theme: AppTheme = AppTheme.PREMIUM
    private var autoSwitch: Boolean = false

    fun updateData(newContributions: List<ContributionDayEntity>) {
        contributions = newContributions
    }

    fun updateTheme(newTheme: AppTheme) {
        theme = newTheme
    }

    fun updateAutoSwitch(enabled: Boolean) {
        autoSwitch = enabled
    }

    fun draw(canvas: Canvas?, width: Int, height: Int) {
        if (canvas == null) return
        val effectiveTheme = ThemeManager.resolveTheme(theme.id, autoSwitch)
        val colors = ThemeManager.getThemeColors(effectiveTheme)

        canvas.drawColor(colors.backgroundColor)

        val cols = 15

        val sidePadding = width * 0.15f
        val usableWidth = width - (2 * sidePadding)

        val cellGapRatio = 0.5f
        val cellDiameter = usableWidth / (cols + (cols - 1) * cellGapRatio)
        val radius = cellDiameter / 2f
        val gap = cellDiameter * cellGapRatio

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR)
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

        val rows = kotlin.math.ceil(daysInYear.toDouble() / cols.toDouble()).toInt()
        val gridWidth = (cols * cellDiameter) + ((cols - 1) * gap)
        val gridHeight = (rows * cellDiameter) + ((rows - 1) * gap)
        val startY = height * 0.28f
        val startX = (width - gridWidth) / 2f

        val dayCounts = contributions.associate { it.date to it.contributionCount }

        val yearIter = Calendar.getInstance().apply {
            set(Calendar.YEAR, currentYear)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 1)
        }

        for (i in 0 until daysInYear) {
            val m = yearIter.get(Calendar.MONTH) + 1
            val d = yearIter.get(Calendar.DAY_OF_MONTH)
            val dateStr = String.format("%04d-%02d-%02d", currentYear, m, d)

            val count = dayCounts[dateStr] ?: 0
            paint.color = getColorForCount(count, colors)

            val row = i / cols
            val col = i % cols

            val cx = startX + radius + col * (cellDiameter + gap)
            val cy = startY + radius + row * (cellDiameter + gap)

            canvas.drawCircle(cx, cy, radius, paint)

            yearIter.add(Calendar.DAY_OF_YEAR, 1)
        }

        // Draw developer quote at the bottom
        val quote = DEV_QUOTES[dayOfYear % DEV_QUOTES.size]
        quotePaint.color = colors.level1Color
        quotePaint.textSize = width * 0.032f
        quotePaint.alpha = 140

        val quoteY = startY + gridHeight + (height * 0.06f)
        canvas.drawText(quote, width / 2f, quoteY, quotePaint)
    }

    private fun getColorForCount(count: Int, themeColors: ThemeColors): Int {
        return when {
            count == 0 -> themeColors.emptyColor
            count in 1..3 -> themeColors.level1Color
            count in 4..6 -> themeColors.level2Color
            count in 7..9 -> themeColors.level3Color
            else -> themeColors.level4Color
        }
    }

    companion object {
        private val DEV_QUOTES = listOf(
            "ship it.",
            "git commit -m \"progress\"",
            "one more commit before bed.",
            "// TODO: sleep",
            "it compiles. ship it.",
            "make it work. make it right. make it fast.",
            "first, solve the problem.",
            "code is poetry.",
            "keep shipping.",
            "debug the world.",
            "sudo make me productive",
            "while(alive) { code(); }",
            "404: excuses not found",
            "trust the process.",
            "build → break → learn → repeat",
            "less talk. more push.",
            "every expert was once a beginner.",
            "stay hungry. stay shipping.",
            "the best code is no code.",
            "think. build. iterate.",
            "your future self will thank you.",
            "consistency > intensity",
            "commit to the craft.",
            "progress, not perfection.",
            "the only way out is through.",
            "simplicity is the ultimate sophistication.",
            "done is better than perfect.",
            "keep your commits atomic.",
            "refactor your life.",
            "hello, world."
        )
    }
}
