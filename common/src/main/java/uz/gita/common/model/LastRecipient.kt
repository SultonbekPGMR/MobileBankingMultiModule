package uz.gita.common.model

data class LastRecipient(
    val pan: String,
    val time:Long,
    val ownerName: String = "",
)
