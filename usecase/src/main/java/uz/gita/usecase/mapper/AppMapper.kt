package uz.gita.usecase.mapper

import uz.gita.common.model.CardData
import uz.gita.common.model.RegisterUserData
import uz.gita.entity.model.request.UserRegisterRequest
import uz.gita.entity.model.response.CardDataResponse


/**
 * Created by Sultonbek Tulanov on 31/12/2024.
 */


internal fun RegisterUserData.toUserRegisterRequest(): UserRegisterRequest {
    return UserRegisterRequest(
        phone = this.phoneNumber,
        password = this.password,
        firstName = this.name,
        lastName = this.surname,
        bornDate = this.age,
        gender = this.gender
    )
}

internal fun CardDataResponse.toCardData(): CardData {
    return CardData(
        id = this.id,
        name = this.name,
        amount = this.amount,
        owner = this.owner,
        pan = this.pan,
        expiredYear = this.expiredYear,
        expiredMonth = this.expiredMonth,
        themeType = this.themeType,
        isVisible = this.isVisible,
    )
}
