package com.githubwallpaper

import org.junit.Test
import java.net.URL
import org.junit.Assert.*

class RegexTest {
    @Test
    fun testRegexMatches() {
        val username = "torvalds"
        val htmlContent = URL("https://github.com/users/${username}/contributions").readText()

        val regex = "<td[^>]*data-date=\"([^\"]+)\"[^>]*data-level=\"([0-4])\"[^>]*>".toRegex()
        val matches = regex.findAll(htmlContent).toList()

        println("Matches found: ${matches.size}")
        assertTrue("Regex should match at least 300 days", matches.size > 300)
    }
}
