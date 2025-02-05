package uz.gita.usecase.usecase.impl

import kotlinx.coroutines.flow.first
import uz.gita.common.model.UserData
import uz.gita.entity.repository.HomeRepository
import uz.gita.usecase.usecase.GetUserInfoUseCase
import uz.gita.usecase.usecase.ShowToastUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

internal class GetUserInfoUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository,
    private val showToastUseCase: ShowToastUseCase,
) : GetUserInfoUseCase {
    override suspend fun invoke(): UserData? {
        val result = homeRepository.getUserInfo().first()
        return result.getOrElse {
            showToastUseCase(it.message ?: "Unknown error occurred")
            null
        }
    }
}