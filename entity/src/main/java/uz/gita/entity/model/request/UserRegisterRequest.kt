package uz.gita.entity.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

data class UserRegisterRequest(
    val phone: String,
    val password: String,
    @SerializedName("first-name") val firstName: String,
    @SerializedName("last-name") val lastName: String,
    @SerializedName("born-date") val bornDate: String,
    val gender: String
)