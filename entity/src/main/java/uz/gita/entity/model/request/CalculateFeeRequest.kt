package uz.gita.entity.model.request

import com.google.gson.annotations.SerializedName

data class CalculateFeeRequest(
    @SerializedName("sender-id") val senderId: String,
    val receiver: String,
    val amount: Int,
    )
