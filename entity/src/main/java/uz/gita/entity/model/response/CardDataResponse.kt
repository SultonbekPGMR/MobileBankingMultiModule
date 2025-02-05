package uz.gita.entity.model.response

import com.google.gson.annotations.SerializedName

data class CardDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("amount") val amount: Long,
    @SerializedName("owner") val owner: String,
    @SerializedName("pan") val pan: String,
    @SerializedName("expired-year") val expiredYear: Int,
    @SerializedName("expired-month") val expiredMonth: Int,
    @SerializedName("theme-type") val themeType: Int,
    @SerializedName("is-visible") val isVisible: Boolean
)