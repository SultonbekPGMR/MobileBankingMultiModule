package uz.gita.common.util

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

inline fun <T> MutableStateFlow<T>.reduce(block: (T) -> T) {
    val old = this.value
    val new = block(old)
    this.value = new
}


fun extractMessage(jsonString: String?): String {
    val jsonObject = jsonString?.let { JSONObject(it) }
    return jsonObject?.getString("message")?:"Unknown Error"
}

fun formatBalance(totalBalance: Double): String {
    val parts = String.format("%.2f", totalBalance).split(".")
    val integerPart = parts[0]
    val decimalPart = parts[1]

    val formattedIntegerPart = integerPart.reversed().chunked(3).joinToString(" ").reversed()
    return "$formattedIntegerPart.$decimalPart UZS"
}
fun formatCardPan(pan: String): String {
    return if (pan.length == 16) {
        "${pan.substring(0, 4)} ${pan.substring(4, 6)}** **** **${pan.substring(14)}"
    } else {
        pan // Return the original if the length is not 16
    }
}
inline fun <T, R> handleApiCall(
    crossinline apiCall: suspend () -> retrofit2.Response<T>,
    crossinline onSuccess: (T) -> R,
    crossinline onError: (String) -> Unit = {},
): Flow<Result<R>> = flow<Result<R>> {
    val response = apiCall()
    if (response.isSuccessful) {
        response.body()?.let {
            emit(Result.success(onSuccess(it)))
        } ?: run {
            onError("Empty response body")
            emit(Result.failure(Exception("Empty response body")))
        }
    } else {
        val errorMessage = extractMessage(response.errorBody()?.string() )
        Log.d("TTTTTOOOOO", "handleApiCall: $errorMessage")
        onError(errorMessage)
        emit(Result.failure(Exception(errorMessage)))
    }
}.flowOn(IO).catch { emit(Result.failure(it)) }


fun checkNumber(number: String): Boolean {
    val uzbekNumberPattern =
        Regex("^(\\+998|998|0)(33|88|90|91|93|94|95|97|98|99|71|72|73|74|75|76|78|79)\\d{7}$")
    return uzbekNumberPattern.matches(number)
}

fun removeSpaces(input:String):String{
    return input.replace(" ", "")
}
fun formatCardNumber(cardNumber: String): String {
    return cardNumber.chunked(4).joinToString(" ")
}

fun formatDateWithHour(day: Int, month: Int, year: Int): String {
    val formattedDay = day.toString().padStart(2, '0')
    val formattedMonth = month.toString().padStart(2, '0')
    val formattedYear = year.toString()

    return "$formattedDay/$formattedMonth/$formattedYear"
}


suspend fun <T> FlowCollector<Result<T>>.emitError(errorBody: ResponseBody?) {
    val errorMessage = errorBody?.string() ?: "Unknown error occurred"
    emit(Result.failure(Throwable(errorMessage)))
}


fun formatPhoneNumber(input: String): String {
    val cleanedNumber = input.replace(" ", "") // Remove spaces
    return "+998$cleanedNumber"
}

@SuppressLint("DefaultLocale")
fun formatSecondsToTime(seconds: Int): String {
    if (seconds == 0) return ""
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}

fun formatDateWithHour(date: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
    return formatter.format(Date(date))
}

fun formatDate(date: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return formatter.format(Date(date))
}




fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd, HH:mm", Locale.getDefault())
    val currentDate = Date()
    return dateFormat.format(currentDate)
}