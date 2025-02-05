package uz.gita.usecase.usecase


/**
 * Created by Sultonbek Tulanov on 11/01/2025.
 */

interface TransferVerifyUseCase {
    suspend operator fun invoke(code: String): Result<Boolean>
}