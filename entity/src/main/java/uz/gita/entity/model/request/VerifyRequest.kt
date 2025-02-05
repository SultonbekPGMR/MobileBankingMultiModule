package uz.gita.entity.model.request

data class VerifyRequest(
    val token:String,
    val code:String
)
