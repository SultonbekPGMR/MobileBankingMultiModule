package uz.gita.mobilebankingmultimodule.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.common.navigator.AppNavigator
import uz.gita.common.navigator.AppNavigatorDispatcher
import uz.gita.common.navigator.AppNavigatorHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun appNavigator(impl: AppNavigatorDispatcher): AppNavigator

    @Binds
    fun appNavigatorHandler(impl: AppNavigatorDispatcher): AppNavigatorHandler
}