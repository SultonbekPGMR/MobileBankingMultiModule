package uz.gita.mobilebankingmultimodule.ui.util

import android.content.Context
import android.content.SharedPreferences
import java.util.Locale

object LanguageChangeHelper {
    private lateinit var pref: SharedPreferences

    fun attach(context: Context): Context {
        pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val savedLanguage = pref.getString("LANGUAGE", "uz") ?: "uz"
        return setLanguage(context, savedLanguage)
    }

    fun setLanguage(context: Context, lang: String): Context {
        pref.edit().putString("LANGUAGE", lang).apply()
        updateResource(context, lang)
        return context
    }

    fun getCurrentLanguage():String{
        return pref.getString("LANGUAGE", "uz")?:"uz"
    }

    private fun updateResource(context: Context, lang: String) {
        val locale = Locale(lang)
        val resource = context.resources

        val configuration = resource.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        context.createConfigurationContext(configuration)
        resource.updateConfiguration(configuration, resource.displayMetrics)
    }
}