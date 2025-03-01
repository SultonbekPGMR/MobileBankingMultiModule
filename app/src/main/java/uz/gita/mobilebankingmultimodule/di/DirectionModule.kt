package uz.gita.mobilebankingmultimodule.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebankingauthcompose.ui.screen.auth.verify.VerifyContract
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract
import uz.gita.mobilebankingmultimodule.direction.*
import uz.gita.presenter.contract.*

/**
 * Created by Sultonbek Tulanov on 25/12/24.
 */

@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {
    @Binds
    fun bindSignInDirections(impl: SignInDirections): SignInContract.Directions

    @Binds
    fun bindSignUpDirections(impl: SignUpDirections): SignUpContract.Directions

    @Binds
    fun bindVerifyDirections(impl: VerifyDirections): VerifyContract.Directions

    @Binds
    fun bindSelectLanguageDirections(impl: SelectLanguageDirections): SelectLanguageContract.Directions


    @Binds
    fun bindPinCodeDirections(impl: PinCodeDirections): PinCodeContract.Directions


    @Binds
    fun bindHomeDirections(impl: HomeDirections): HomeScreenContract.Directions

    @Binds
    fun bindTransferMoneyDirections(impl: TransferMoneyDirections): TransferMoneyScreenContract.Directions

    @Binds
    fun bindUserDetailsDirections(impl: UserDetailsDirections): UserDetailsScreenContract.Directions


    @Binds
    fun bindAddCardDirections(impl: AddCardDirections): AddCardContract.Directions


    @Binds
    fun bindConfirmTransferDirections(impl: ConfirmTransferDirections): ConfirmTransferScreenContract.Directions


    @Binds
    fun bindTransferCompletedDirections(impl: TransferCompletedDirections): TransferCompletedContract.Directions


    @Binds
    fun bindMonitoringDirections(impl: MonitoringDirections): MonitoringScreenContract.Directions

    @Binds
    fun bindSplashDirections(impl: SplashDirections): SplashContract.Directions

    @Binds
    fun bindTransfersDirections(impl: TransfersDirections): TransfersContract.Directions

    @Binds
    fun bindSettingsDirections(impl: SettingsDirections): SettingsContract.Directions

    @Binds
    fun bindCardDetailsDirections(impl: CardDetailsDirections): CardDetailsContract.Directions


}