package uz.gita.entity.repository.impl

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.gita.common.util.handleApiCall
import uz.gita.entity.local.Preferences
import uz.gita.entity.model.request.ResendCodeRequest
import uz.gita.entity.model.request.UserRegisterRequest
import uz.gita.entity.model.request.UserSignInRequest
import uz.gita.entity.model.request.VerifyRequest
import uz.gita.entity.remote.api.AuthApi
import uz.gita.entity.repository.AuthRepository
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

internal class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val preferences: Preferences,
) : AuthRepository {

    init {
        Log.d("BBBB", "preferences: ${preferences.verificationToken}")
    }

    override fun registerUser(userRegisterRequest: UserRegisterRequest): Flow<Result<Unit>> =
        handleApiCall(
            apiCall = { authApi.registerUser(userRegisterRequest) },
            onSuccess = {
                preferences.verificationToken = it.token
                Log.d("BBBB", "registerUser: success")
            }
        )

    override fun signInUser(userSignInRequest: UserSignInRequest): Flow<Result<Unit>> =
        handleApiCall(
            apiCall = { authApi.signInUser(userSignInRequest) },
            onSuccess = {
                preferences.verificationToken = it.token
                Log.d("BBBB", "signInUser: success")
            }
        )

    override fun verifyRegisterPhoneNumber(code: String): Flow<Result<Boolean>> =
        handleApiCall(
            apiCall = {
                authApi.verifyRegisterSMS(VerifyRequest(preferences.verificationToken, code))
            },
            onSuccess = {
                preferences.refreshToken = it.refreshToken
                preferences.accessToken = it.accessToken
                preferences.isUserSignedIn = true
                true
            },
            onError = { Log.e("Verify", it) }
        )

    override fun resendRegistrationVerifyCode(): Flow<Result<Unit>> =
        handleApiCall(
            apiCall = { authApi.resendSignUpCode(ResendCodeRequest(preferences.verificationToken)) },
            onSuccess = {
                preferences.verificationToken = it.token
            }
        )

    override fun resendSignInVerifyCode(): Flow<Result<Unit>> =
        handleApiCall(
            apiCall = { authApi.resendSignInCode(ResendCodeRequest(preferences.verificationToken)) },
            onSuccess = {
                preferences.verificationToken = it.token
            }
        )

    override fun logOutUser() {
        preferences.verificationToken = ""
        preferences.accessToken = ""
        preferences.refreshToken = ""
        preferences.isUserSignedIn = false
        preferences.pinCode = ""
        preferences.useBiometricsToEnterApp = false
    }

    override fun verifySignInPhoneNumber(code: String): Flow<Result<Boolean>> =
        handleApiCall(
            apiCall = {
                authApi.verifySignInSMS(VerifyRequest(preferences.verificationToken, code))
            },
            onSuccess = {
                preferences.refreshToken = it.refreshToken
                preferences.accessToken = it.accessToken
                preferences.isUserSignedIn = true
                true
            }
        )
}
