package uz.gita.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

sealed class VerifyType : Parcelable, Serializable {
        @Parcelize
        data class Transfer(val transferData: TransferData) : VerifyType()
        @Parcelize
        data class Auth(val isSignInVerify: Boolean = false) : VerifyType()
    }