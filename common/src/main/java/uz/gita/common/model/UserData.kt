package uz.gita.common.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

data class UserData(
    @SerializedName("first-name") val firstName: String,
    @SerializedName("last-name") val lastName: String,
    val phone: String,
    @SerializedName("born-date") val bornDate: Long,
    val gender: Int
)