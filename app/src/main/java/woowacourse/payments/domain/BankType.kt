package woowacourse.payments.domain

import woowacourse.payments.ui.model.BankTypeUiModel

enum class BankType {
    NOT_SELECTED,
    BC,
    SINHAN,
    KAKAO,
    HYUNDE,
    WOORI,
    LOTTE,
    HANA,
    KB,
    ;

    fun toUiModel(): BankTypeUiModel =
        when (this) {
            NOT_SELECTED -> BankTypeUiModel.NOT_SELECTED
            BC -> BankTypeUiModel.BC
            SINHAN -> BankTypeUiModel.SINHAN
            KAKAO -> BankTypeUiModel.KAKAO
            HYUNDE -> BankTypeUiModel.HYUNDE
            WOORI -> BankTypeUiModel.WOORI
            LOTTE -> BankTypeUiModel.LOTTE
            HANA -> BankTypeUiModel.HANA
            KB -> BankTypeUiModel.KB
        }
}
