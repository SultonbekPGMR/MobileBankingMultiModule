package uz.gita.common.model


/**
 * Created by Sultonbek Tulanov on 02/01/2025.
 */

data class  CardData(
    val id: Int,
    val name: String,
    val amount: Long,
    val owner  : String,
    val pan: String = "123",
    val expiredYear: Int,
    val expiredMonth: Int,
    val themeType: Int = 0,
    val isVisible: Boolean = true,
    val bankName:String = ""
)
