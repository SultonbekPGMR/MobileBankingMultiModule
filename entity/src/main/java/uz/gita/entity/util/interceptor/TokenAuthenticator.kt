package uz.gita.entity.util.interceptor

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.entity.local.Preferences
import uz.gita.entity.model.request.RefreshTokenRequest
import uz.gita.entity.remote.api.ApiService


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal class TokenAuthenticator(context: Context, private val preferences: Preferences) : Authenticator {
    private val apiService: ApiService
    private val lock = Any()
    private var isRefreshing = false

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://185.193.17.169:8080/mobile-bank/v1/")
            .client(OkHttpClient.Builder().addInterceptor(ChuckerInterceptor(context)).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    override fun authenticate(route: Route?, response: Response): Request {

        synchronized(lock) {
            if (!isRefreshing) {
                isRefreshing = true
                try {
                    val newAccessToken = getUpdatedToken()

                    return response.request.newBuilder()
                        .header("Authorization", "Bearer $newAccessToken")
                        .build()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    isRefreshing = false
                }
            }
        }

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${preferences.accessToken}")
            .build()

    }

    private fun getUpdatedToken(): String {
        val authTokenResponse =
            apiService.refreshToken(RefreshTokenRequest(preferences.refreshToken)).execute()
                .body();

        Log.d("TTTPTOTOOTO", "getUpdatedToken: $authTokenResponse")
        val newToken = authTokenResponse?.accessToken
        authTokenResponse?.let {
            preferences.refreshToken = it.refreshToken
            preferences.accessToken = it.accessToken
        }
        return newToken ?: preferences.accessToken
    }
}