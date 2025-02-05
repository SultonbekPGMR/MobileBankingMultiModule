package uz.gita.entity.util.interceptor

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.entity.local.Preferences
import uz.gita.entity.model.request.RefreshTokenRequest
import uz.gita.entity.model.response.RefreshAccessTokenResponse
import uz.gita.entity.remote.api.ApiService


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal class AuthorizerInterceptor(
    private val preferences: Preferences,
    private val context: Context,
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        var response = chain.proceed(originalRequest)

        if (response.code == 401) {
            CoroutineScope(Dispatchers.IO).launch {
                val refreshedTokens = refreshTokens()

                if (refreshedTokens != null) {
                    preferences.accessToken = refreshedTokens.accessToken
                    preferences.refreshToken = refreshedTokens.refreshToken

                    val newRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer ${preferences.accessToken}").build()

                    response = chain.proceed(newRequest)
                } else {
                    response = response.newBuilder().build()
                }
            }

        }

        return response
    }


    private suspend fun refreshTokens(): RefreshAccessTokenResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://185.193.17.169:8080/mobile-bank/v1/") // replace with the actual base URL
            .client(OkHttpClient.Builder().addInterceptor(ChuckerInterceptor(context)).build())
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiService = retrofit.create(ApiService::class.java)

         apiService.refreshToken(
            RefreshTokenRequest(
                preferences.refreshToken
            )
        )
        Log.d("TTTTEEE", "refreshTokens: ")


        return null
    }


}
