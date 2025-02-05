package uz.gita.entity.model.response

import com.google.gson.annotations.SerializedName
import uz.gita.common.model.TransactionData

data class HistoryResponse(
    @SerializedName("tot al-elements") val totalElements: Int,
    @SerializedName("total-pages") val totalPages: Int,
    @SerializedName("current-page") val currentPage: Int,
    @SerializedName("child") val transactions: List<TransactionData>,
)