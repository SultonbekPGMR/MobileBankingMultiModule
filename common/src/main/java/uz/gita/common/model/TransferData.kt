package uz.gita.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
 class TransferData(
   val senderPan: String = "",
   val senderId: String = "",
   val senderBalance: String = "",
   val receiverPan: String = "",
   val receiverName: String = "",
   val amount: String = "",
   val total: Double = 0.0,
   val commission: String = "",
   var date:String = ""
) : Parcelable, Serializable
