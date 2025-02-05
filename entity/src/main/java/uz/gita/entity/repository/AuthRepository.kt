package uz.gita.entity.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.entity.model.request.UserRegisterRequest
import uz.gita.entity.model.request.UserSignInRequest


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

interface AuthRepository {

    fun registerUser(userRegisterRequest: UserRegisterRequest): Flow<Result<Unit>>
    fun signInUser(userSignInRequest: UserSignInRequest): Flow<Result<Unit>>
    fun verifyRegisterPhoneNumber(code: String): Flow<Result<Boolean>>
    fun verifySignInPhoneNumber(code: String): Flow<Result<Boolean>>
    fun resendRegistrationVerifyCode(): Flow<Result<Unit>>
    fun resendSignInVerifyCode(): Flow<Result<Unit>>
    fun logOutUser()

}