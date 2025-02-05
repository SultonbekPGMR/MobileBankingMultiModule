package uz.gita.usecase.usecase


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

interface CalculateCommissionUseCase {
    operator fun invoke(amount: Double): Double
}