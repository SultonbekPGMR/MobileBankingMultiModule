package uz.gita.entity.model.request

import com.google.gson.annotations.SerializedName

data class NewCardRequest(
    @SerializedName("pan") val pan: String,
    @SerializedName("expired-year") val expiredYear: String,
    @SerializedName("expired-month") val expiredMonth: String,
    @SerializedName("name") val name: String
)
