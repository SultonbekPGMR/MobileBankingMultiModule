package uz.gita.mobilebankingmultimodule.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Created by Sultonbek Tulanov on 30/01/2025.
 */
fun formatMillisToFullDate(millis: Long): String {
    val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
    return sdf.format(Date(millis))
}