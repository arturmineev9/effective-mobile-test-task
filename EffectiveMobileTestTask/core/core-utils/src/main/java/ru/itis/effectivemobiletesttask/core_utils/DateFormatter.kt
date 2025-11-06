package ru.itis.effectivemobiletesttask.core_utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormatter {
    private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))

    fun formatPublishDate(dateString: String): String {
        return try {
            val date = LocalDate.parse(dateString, inputFormatter)
            date.format(outputFormatter)
        } catch (e: Exception) {
            dateString
        }
    }
}