package uz.gita.entity.util.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import uz.gita.entity.local.Preferences
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

@Singleton
internal class HeaderInterceptor @Inject constructor(
    private val preferences: Preferences,
) : Interceptor {
    override fun intercept(chain: Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${preferences.accessToken}")
            .build()
        return chain.proceed(request)
    }
}