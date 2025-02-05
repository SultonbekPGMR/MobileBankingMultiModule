package uz.gita.entity.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.entity.repository.AuthRepository
import uz.gita.entity.repository.CardRepository
import uz.gita.entity.repository.HomeRepository
import uz.gita.entity.repository.TransferRepository
import uz.gita.entity.repository.impl.AuthRepositoryImpl
import uz.gita.entity.repository.impl.CardRepositoryImpl
import uz.gita.entity.repository.impl.HomeRepositoryImpl
import uz.gita.entity.repository.impl.TransferRepositoryImpl
import javax.inject.Singleton


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @[Binds Singleton]
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository


    @[Binds Singleton]
    fun bindCardRepository(impl: CardRepositoryImpl): CardRepository


    @[Binds Singleton]
    fun bindTransferRepository(impl: TransferRepositoryImpl): TransferRepository


    @[Binds Singleton]
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository



}