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
    
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    }
    
    private var contributions: List<ContributionDayEntity> = emptyList()
    private var theme: AppTheme = AppTheme.PREMIUM

    fun updateData(newContributions: List<ContributionDayEntity>) {
        contributions = newContributions
    }

    fun updateTheme(newTheme: AppTheme) {
        theme = newTheme
    }

    fun draw(canvas: Canvas?, width: Int, height: Int) {
        if (canvas == null) return
        val colors = ThemeManager.getThemeColors(theme)
        
        // Background
        canvas.drawColor(colors.backgroundColor)
        
        // 365/366 days fit into 15 columns and 25 rows.
        val cols = 15
        
        // Dynamic sizing based on screen width scaling
        val sidePadding = width * 0.15f
        val usableWidth = width - (2 * sidePadding)
        
        val cellGapRatio = 0.5f 
        val cellDiameter = usableWidth / (cols + (cols - 1) * cellGapRatio)
        val radius = cellDiameter / 2f
        val gap = cellDiameter * cellGapRatio

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR)
        val rows = Math.ceil(daysInYear.toDouble() / cols.toDouble()).toInt()
        
        val gridWidth = (cols * cellDiameter) + ((cols - 1) * gap)
        val gridHeight = (rows * cellDiameter) + ((rows - 1) * gap)
        
        val startY = height * 0.28f
        val startX = (width - gridWidth) / 2f
        
        val dayCounts = contributions.associate { it.date to it.contributionCount }
        
        // Iterator for current year
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
        
        // Draw bottom text
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val daysLeft = daysInYear - dayOfYear
        val percentage = (dayOfYear.toFloat() / daysInYear.toFloat() * 100).toInt()
        
        val progressText = "${daysLeft}d left • $percentage%"
        
        textPaint.color = colors.level2Color // Using orange theme color for text
        textPaint.textSize = width * 0.045f
        textPaint.alpha = 200 // Slight transparency for elegance
        
        val textY = startY + gridHeight + (height * 0.08f)
        canvas.drawText(progressText, width / 2f, textY, textPaint)
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
}
