package uz.gita.entity.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.entity.model.request.ResendCodeRequest
import uz.gita.entity.model.request.UserRegisterRequest
import uz.gita.entity.model.request.UserSignInRequest
import uz.gita.entity.model.request.VerifyRequest
import uz.gita.entity.model.response.RefreshAccessTokenResponse
import uz.gita.entity.model.response.VerifyToken


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

internal interface AuthApi {

    @POST("auth/sign-up")
    suspend fun registerUser(@Body registerUserRequest: UserRegisterRequest): Response<VerifyToken>

    @POST("auth/sign-in")
    suspend fun signInUser(@Body signInRequest: UserSignInRequest): Response<VerifyToken>

    @POST("auth/sign-up/verify")
    suspend fun verifyRegisterSMS(@Body code: VerifyRequest): Response<RefreshAccessTokenResponse>

    @POST("auth/sign-up/resend")
    suspend fun resendSignUpCode(@Body token: ResendCodeRequest): Response<VerifyToken>

    @POST("auth/sign-in/resend")
    suspend fun resendSignInCode(@Body token: ResendCodeRequest): Response<VerifyToken>

    @POST("auth/sign-in/verify")
    suspend fun verifySignInSMS(@Body code: VerifyRequest): Response<RefreshAccessTokenResponse>


}