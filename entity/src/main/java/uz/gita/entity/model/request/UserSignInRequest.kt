package uz.gita.entity.model.request

data class UserSignInRequest(
    val phone: String,
    val password: String,
)
