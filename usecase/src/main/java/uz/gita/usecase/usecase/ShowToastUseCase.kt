package uz.gita.usecase.usecase


/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */

interface ShowToastUseCase {
    suspend operator fun invoke(message: String)
}