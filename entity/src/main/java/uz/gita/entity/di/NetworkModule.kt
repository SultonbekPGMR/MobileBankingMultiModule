package uz.gita.entity.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.gita.entity.local.Preferences
import uz.gita.entity.util.interceptor.HeaderInterceptor
import uz.gita.entity.util.interceptor.TokenAuthenticator
import uz.gita.entity.remote.api.AuthApi
import uz.gita.entity.remote.api.CardApi
import uz.gita.entity.remote.api.HomeApi
import uz.gita.entity.remote.api.TransferApi
import javax.inject.Singleton


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    private val cacheSize = (5 * 1024 * 1024).toLong()

    @[Provides Singleton]
    fun provideOkHttp(
        @ApplicationContext context: Context,
        preferences: Preferences,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .authenticator(TokenAuthenticator(context, preferences))
        .addInterceptor(HeaderInterceptor(preferences))
        .cache(Cache(context.cacheDir, cacheSize))
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            chain.proceed(request)
        }
        .build()

    @[Provides Singleton]
    fun provideApiClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://185.193.17.169:8080/mobile-bank/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)

        .build()


    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create()

    @[Provides Singleton]
    fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create()


    @[Provides Singleton]
    fun provideCardApi(retrofit: Retrofit): CardApi = retrofit.create()


    @[Provides Singleton]
    fun provideTransferApi(retrofit: Retrofit): TransferApi = retrofit.create()

    private fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}