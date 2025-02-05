package uz.gita.usecase.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.CardData


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

interface GetAllCardsUseCase {
    operator fun invoke(): Flow<Result<List<CardData>>>
}