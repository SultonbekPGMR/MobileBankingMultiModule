package uz.gita.entity.model.request

import com.google.gson.annotations.SerializedName

data class TransferRequest(
    @SerializedName("type") val type: String,
    @SerializedName("sender-id") val senderId: String,
    @SerializedName("receiver-pan") val receiverPan: String,
    @SerializedName("amount") val amount: Int
)
