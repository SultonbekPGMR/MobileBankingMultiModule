package uz.gita.entity.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

data class RefreshTokenRequest(
  @SerializedName("refresh-token")  val token:String
)