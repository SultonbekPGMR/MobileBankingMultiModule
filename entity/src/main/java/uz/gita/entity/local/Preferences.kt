package uz.gita.entity.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.entity.util.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Sultonbek Tulanov on 06/12/2024.
 */

@Singleton
 class Preferences @Inject constructor(@ApplicationContext context: Context) :
    SharedPreference(context) {

    var accessToken: String by Strings()
    var refreshToken: String by Strings()
    var verificationToken: String by Strings()
    var isUserSignedIn: Boolean by Booleans()
    var userPhoneNumber: String by Strings()
    var pinCode: String by Strings()
    var appLanguage: String by Strings()
    var useBiometricsToEnterApp: Boolean by Booleans()

}