package uz.gita.usecase.usecase

import uz.gita.common.model.UserData


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

interface GetUserInfoUseCase {
    suspend operator fun invoke(): UserData?
}