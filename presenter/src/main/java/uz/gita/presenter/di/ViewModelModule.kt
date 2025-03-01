package uz.gita.presenter.di

import android.provider.Settings
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelFactory
import cafe.adriel.voyager.hilt.ScreenModelFactoryKey
import cafe.adriel.voyager.hilt.ScreenModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import uz.gita.mobilebankingauthcompose.presenter.viewmodel.TransferCompletedViewModel
import uz.gita.presenter.viewmodel.*


/**
 * Created by Sultonbek Tulanov on 31/12/2024.
 */


@Module
@InstallIn(SingletonComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ScreenModelFactoryKey(VerifyViewModel.Factory::class)
    fun bindVerifyViewModel(impl: VerifyViewModel.Factory): ScreenModelFactory


    @Binds
    @IntoMap
    @ScreenModelFactoryKey(PinCodeViewModel.Factory::class)
    fun bindPinCodeViewModel(impl: PinCodeViewModel.Factory): ScreenModelFactory


    @Binds
    @IntoMap
    @ScreenModelFactoryKey(CardDetailsViewModel.Factory::class)
    fun bindCardDetailsViewModel(impl: CardDetailsViewModel.Factory): ScreenModelFactory


    @[Binds IntoMap ScreenModelKey(SplashVIewModel::class)]
    fun bindSplashVIewModel(impl: SplashVIewModel): ScreenModel


    @[Binds IntoMap ScreenModelKey(SelectLanguageViewModel::class)]
    fun bindSelectLanguageViewModel(impl: SelectLanguageViewModel): ScreenModel


    @[Binds IntoMap ScreenModelKey(SignInViewModel::class)]
    fun bindSignInViewModel(impl: SignInViewModel): ScreenModel


    @[Binds IntoMap ScreenModelKey(SignUpViewModel::class)]
    fun bindSignUpViewModel(impl: SignUpViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(MainScreenViewModel::class)]
    fun bindMainScreenViewModel(impl: MainScreenViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(HomeViewModel::class)]
    fun bindHomeViewModel(impl: HomeViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(TransferMoneyScreenViewModel::class)]
    fun bindTransferMoneyScreenViewModel(impl: TransferMoneyScreenViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(UserDetailsScreenViewModel::class)]
    fun bindUserDetailsScreenViewModel(impl: UserDetailsScreenViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(AddCardViewModel::class)]
    fun bindAddCardScreenViewModel(impl: AddCardViewModel): ScreenModel


    @[Binds IntoMap ScreenModelKey(ConfirmTransferViewModel::class)]
    fun bindConfirmTransferScreenViewModel(impl: ConfirmTransferViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(TransferCompletedViewModel::class)]
    fun bindTransferCompletedScreenViewModel(impl: TransferCompletedViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(MonitoringScreenViewModel::class)]
    fun bindMonitoringScreenViewModel(impl: MonitoringScreenViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(TransfersViewModel::class)]
    fun bindTransfersViewModel(impl: TransfersViewModel): ScreenModel

    @[Binds IntoMap ScreenModelKey(SettingsViewModel::class)]
    fun bindSettingsViewModel(impl: SettingsViewModel): ScreenModel


}