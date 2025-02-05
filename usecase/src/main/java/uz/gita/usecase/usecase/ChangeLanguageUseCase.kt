package uz.gita.usecase.usecase

import android.content.Context


/**
 * Created by Sultonbek Tulanov on 05/12/2024.
 */

interface ChangeLanguageUseCase {
    operator fun invoke(context: Context,langCode:String)
}