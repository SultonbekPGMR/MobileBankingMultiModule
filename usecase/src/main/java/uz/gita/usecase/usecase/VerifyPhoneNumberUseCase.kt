package uz.gita.usecase.usecase


/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */

interface VerifyPhoneNumberUseCase {
    suspend operator fun invoke(isSignInVerify: Boolean, code: String): Result<Boolean>
}