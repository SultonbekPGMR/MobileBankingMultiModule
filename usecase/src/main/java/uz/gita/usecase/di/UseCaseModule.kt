package uz.gita.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebankingauthcompose.domain.usecase.TransferUseCase
import uz.gita.mobilebankingauthcompose.domain.usecase.impl.SignInUserUseCaseImpl
import uz.gita.usecase.usecase.*
import uz.gita.usecase.usecase.impl.*
import javax.inject.Singleton


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {


    @[Binds Singleton]
    fun bindRegisterUserUseCase(impl: RegisterUserUseCaseImpl): RegisterUserUseCase


    @[Binds Singleton]
    fun bindShowToastUseCase(impl: ShowToastUseCaseImpl): ShowToastUseCase

    @[Binds Singleton]
    fun bindVerifyUseCase(impl: VerifyPhoneNumberUseCaseImpl): VerifyPhoneNumberUseCase

    @[Binds Singleton]
    fun bindResendVerificationCodeUseCase(impl: ResendVerifyCodeUseCaseImpl): ResendVerifyCodeUseCase

    @[Binds Singleton]
    fun bindSignInUserUseCase(impl: SignInUserUseCaseImpl): SignInUserUseCase


    @[Binds Singleton]
    fun bindAddCardUseCase(impl: AddCardUseCaseImpl): AddCardUseCase


    @[Binds Singleton]
    fun bindGetCardsUseCase(impl: GetAllCardsUseCaseImpl): GetAllCardsUseCase


    @[Binds Singleton]
    fun bindGetTotalBalanceUseCase(impl: GetTotalBalanceUseCaseImpl): GetTotalBalanceUseCase

    @[Binds Singleton]
    fun bindGetCardOwnerByPanUseCase(impl: GetCardOwnerByPanUseCaseImpl): GetCardOwnerByPanUseCase

    @[Binds Singleton]
    fun bindTransferUseCase(impl: TransferUseCaseImpl): TransferUseCase


    @[Binds Singleton]
    fun bindTransferVerifyUseCase(impl: TransferVerifyUseCaseImpl): TransferVerifyUseCase


    @[Binds Singleton]
    fun bindCalculateCommissionUseCase(impl: CalculateCommissionUseCaseImpl): CalculateCommissionUseCase


    @[Binds Singleton]
    fun bindGetHistoryUseCase(impl: GetHistoryUseCaseImpl): GetHistoryUseCase


    @[Binds Singleton]
    fun bindCalculateHistoryUseCase(impl: CalculateHistoryUseCaseImpl): CalculateHistoryUseCase


    @[Binds Singleton]
    fun bindGetUserInfoUseCase(impl: GetUserInfoUseCaseImpl): GetUserInfoUseCase


    @[Binds Singleton]
    fun bindGetLastRecipientsUseCase(impl: GetLastRecipientsUseCaseImpl): GetLastRecipientsUseCase


    @[Binds Singleton]
    fun bindUserPreferenceUseCase(impl: UserPreferenceUseCaseImpl): UserPreferenceUseCase

    @[Binds Singleton]
    fun bindLogOutUserUseCase(impl: LogOutUserUseCaseImpl): LogOutUserUseCase


}