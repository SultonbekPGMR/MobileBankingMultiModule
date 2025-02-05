package uz.gita.entity.model.response

import com.google.gson.annotations.SerializedName

data class RefreshAccessTokenResponse(
    @SerializedName("refresh-token") val refreshToken:String,
    @SerializedName("access-token") val accessToken:String,
)
