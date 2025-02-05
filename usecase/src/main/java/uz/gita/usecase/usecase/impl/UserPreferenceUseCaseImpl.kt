package uz.gita.usecase.usecase.impl

import uz.gita.entity.local.Preferences
import uz.gita.usecase.usecase.UserPreferenceUseCase
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 18/01/2025.
 */

internal class UserPreferenceUseCaseImpl @Inject constructor(
    private val preferences: Preferences,
) : UserPreferenceUseCase {
    override fun isBiometricsEnabled(): Boolean {
        return preferences.useBiometricsToEnterApp
    }

    override fun setBiometricsChange(enabled: Boolean) {
        preferences.useBiometricsToEnterApp = enabled
    }

    override fun checkPinCode(pinCode: String): Boolean {
        return preferences.pinCode == pinCode
    }

    override fun setPinCode(newPinCode: String) {
        preferences.pinCode = newPinCode
    }

    override fun isPinCodeSet(): Boolean {
        return preferences.pinCode.isNotEmpty()
    }

    override fun setUserPhoneNumber(phoneNumber: String) {
        preferences.userPhoneNumber = phoneNumber
    }

    override fun getUserPhoneNumber(): String {
        return preferences.userPhoneNumber
    }

    override fun isUserSignedIn(): Boolean {
        return preferences.isUserSignedIn
    }

}