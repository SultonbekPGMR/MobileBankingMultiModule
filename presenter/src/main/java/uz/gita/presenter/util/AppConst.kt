package uz.gita.presenter.util

import uz.gita.common.model.MainServiceData
import uz.gita.common.model.SuggestedActionBanner
import uz.gita.mobilebankingauthcompose.ui.screen.userdetails.UserDetailsScreenContract.OptionSettings
import uz.gita.presenter.R

internal object AppConst {
    val mainMoneyServices: List<MainServiceData> = listOf(
        MainServiceData(nameId = R.string.lbl_my_pension, iconId = R.drawable.ic_coin_font),
        MainServiceData(nameId = R.string.lbl_inps, iconId = R.drawable.ic_news),
        MainServiceData(nameId = R.string.lbl_my_bank, iconId = R.drawable.ic_building_bank),
        MainServiceData(nameId = R.string.lbl_transfers, iconId = R.drawable.ic_cash_svg),
        MainServiceData(nameId = R.string.lbl_my_home, iconId = R.drawable.ic_home_green),
        MainServiceData(nameId = R.string.my_number, iconId = R.drawable.ic_phone_call),
    )
    val userDetailsScreenOptionsList = listOf(
        OptionSettings(iconId = R.drawable.ic_rocket_selected,titleId = R.string.lbl_settings),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.lbl_general_info),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.lbl_contact_the_bank),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.invite_your_friend),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.lbl_exit),
    )

       val settingsScreenOptionsList = listOf(
        OptionSettings(iconId = R.drawable.ic_rocket_selected,titleId = R.string.security),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.widget_settings),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.theme),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.lbl_language),
        OptionSettings(iconId = R.drawable.ic_rocket_selected, titleId = R.string.about_app),
    )



    val bannerList = listOf(
        SuggestedActionBanner(
            titleId = R.string.education_loan,
            contentId = R.string.asking_for_a_loan,
            iconId = R.drawable.ic_rocket_selected
        ),
        SuggestedActionBanner(
            titleId = R.string.auto_insurance,
            contentId = R.string.get_auto_insurance,
            iconId = R.drawable.ic_rocket_selected
        ),
        SuggestedActionBanner(
            titleId = R.string.lbl_birth_benefit,
            contentId = R.string.info_birth_benefit,
            iconId = R.drawable.ic_rocket_selected
        ),
        SuggestedActionBanner(
            titleId = R.string.bank,
            contentId = R.string.info_birth_benefit,
            iconId = R.drawable.ic_rocket_selected
        ),
    )
    val transferScreenOptions:List<MainServiceData> = listOf(
        MainServiceData(nameId = R.string.transfer_to_card, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.qr_share, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.lbl_transfer_wallets, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.transfer_between_my_cards, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.transfer_by_requisites, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.transfer_conversion, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.international_transfers, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.lbl_ask_money, iconId = R.drawable.ic_rocket_selected),
        MainServiceData(nameId = R.string.asking_for_a_loan, iconId = R.drawable.ic_rocket_selected),
    )

}



