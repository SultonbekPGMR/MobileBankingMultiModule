package uz.gita.usecase.usecase.impl

import uz.gita.usecase.usecase.CalculateCommissionUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

internal class CalculateCommissionUseCaseImpl @Inject constructor() : CalculateCommissionUseCase {
    override fun invoke(amount: Double): Double {
        return amount.div(100)
    }
}