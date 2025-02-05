package uz.gita.common.model

data class RegisterUserData(
    val phoneNumber: String,
    val name: String,
    val surname: String,
    val age: String,
    val gender: String,
    val password: String,
)