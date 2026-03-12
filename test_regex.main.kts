import java.io.File
import java.net.URL

fun main() {
    val username = "torvalds"
    val htmlContent = URL("https://github.com/users/${username}/contributions").readText()

    val regex = "<td[^>]*data-date=\"([^\"]+)\"[^>]*data-level=\"([0-4])\"[^>]*>".toRegex()
    val matches = regex.findAll(htmlContent).toList()

    println("Matches found: ${matches.size}")
    if (matches.isNotEmpty()) {
        println("First match: Date=${matches[0].groupValues[1]}, Level=${matches[0].groupValues[2]}")
    } else {
        println("Regex failed!")
        // Print snippet of the actual table
        val tbodyIndex = htmlContent.indexOf("<tbody>")
        if (tbodyIndex != -1) {
            println(htmlContent.substring(tbodyIndex, minOf(tbodyIndex + 1000, htmlContent.length)))
        }
    }
}
