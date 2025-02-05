package uz.gita.entity.repository.impl

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.entity.local.Preferences
import uz.gita.entity.model.request.UserRegisterRequest
import uz.gita.entity.model.request.UserSignInRequest
import uz.gita.entity.remote.api.AuthApi
import uz.gita.entity.repository.AuthRepository
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

internal class AuthRepositoryFakeImpl @Inject constructor(
    private val authApi: AuthApi,
    private val preferences: Preferences,
) : AuthRepository {

    init {
        Log.d("BBBB", "preferences: ${preferences.verificationToken}")
    }

    private val delayTime = 777L


    override fun registerUser(userRegisterRequest: UserRegisterRequest): Flow<Result<Unit>> = flow {
        delay(delayTime)
        emit(Result.success(Unit))
    }


    override fun signInUser(userSignInRequest: UserSignInRequest): Flow<Result<Unit>> = flow {
        delay(delayTime)
        emit(Result.success(Unit))
    }

    override fun verifyRegisterPhoneNumber(code: String): Flow<Result<Boolean>> = flow {
        delay(delayTime)

        emit(Result.success(true))

    }

    override fun resendRegistrationVerifyCode(): Flow<Result<Unit>> = flow {
        delay(delayTime)
        emit(Result.success(Unit))
    }

    override fun resendSignInVerifyCode(): Flow<Result<Unit>> =
        flow {
            delay(delayTime)
            emit(Result.success(Unit))
        }

    override fun logOutUser() {
        preferences.verificationToken = ""
        preferences.accessToken = ""
        preferences.refreshToken = ""
        preferences.isUserSignedIn = false
        preferences.pinCode = ""
        preferences.useBiometricsToEnterApp = false
    }

    override fun verifySignInPhoneNumber(code: String): Flow<Result<Boolean>> =
        flow {
            delay(delayTime)
            preferences.isUserSignedIn = true

            emit(Result.success(true))
        }
}
