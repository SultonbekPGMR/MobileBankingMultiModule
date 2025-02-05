package uz.gita.usecase.usecase

import uz.gita.common.model.VerifyType


/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */

interface ResendVerifyCodeUseCase {
    suspend operator fun invoke(verifyType: VerifyType):Result<Unit>
}