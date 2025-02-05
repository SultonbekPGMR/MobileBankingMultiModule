package uz.gita.entity.remote.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.entity.model.request.RefreshTokenRequest
import uz.gita.entity.model.response.RefreshAccessTokenResponse

interface ApiService {
        @POST("auth/update-token")
        fun refreshToken(
            @Body refreshTokenRequest: RefreshTokenRequest,
        ): Call<RefreshAccessTokenResponse>
    }