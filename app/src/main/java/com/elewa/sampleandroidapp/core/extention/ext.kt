package com.elewa.sampleandroidapp.core.extention

import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}
fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}
fun String.toDate(
    dateTimeFormat: String = "MM dd yyyy hh:mm:ss",
//    dateTimeFormat: String = "yyyy-MM-dd",
    languageCode:String="en",
    timeZone: String = "UTC"
): Date? {
    val df = SimpleDateFormat(dateTimeFormat, Locale(languageCode))
    df.timeZone = TimeZone.getTimeZone(timeZone)
    return try {
        df.parse(this)
    } catch (e: ParseException) {
        null
    }

}

fun String.toFormattedString(): String {
    val oldFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss'UTC'")
    oldFormatter.timeZone = TimeZone.getTimeZone("UTC")
    var value: Date? = null
    var dueDateAsNormal = ""
    try {
        value = oldFormatter.parse(this)
        val newFormatter = SimpleDateFormat("MM/dd/yyyy - hh:mm a")
        newFormatter.timeZone = TimeZone.getDefault()
        dueDateAsNormal = newFormatter.format(value)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return dueDateAsNormal
}

fun String.toDate(dateFormat: String = "MMM dd, yyyy hh:mm:ss", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String = "MM/dd/yyyy - hh:mm a", timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}
