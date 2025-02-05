package uz.gita.usecase.usecase


/**
 * Created by Sultonbek Tulanov on 18/01/2025.
 */

interface UserPreferenceUseCase {
    fun isBiometricsEnabled(): Boolean
    fun setBiometricsChange(enabled: Boolean)
    fun checkPinCode(pinCode: String): Boolean
    fun setPinCode(newPinCode: String)
    fun isPinCodeSet(): Boolean
    fun setUserPhoneNumber(phoneNumber: String)
    fun getUserPhoneNumber():String
    fun isUserSignedIn(): Boolean

}