package uz.gita.common.model

data class TransactionData(
    val type: String,
    val from: String,
    val to: String,
    val amount: Int,
    val time: Long
)