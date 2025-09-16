package woowacourse.payments.ui.util

import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.CardCompanyUiModel

fun BankType.toCardCompanyUiModel(): CardCompanyUiModel =
    when (this) {
        BankType.BC ->
            CardCompanyUiModel.create(
                name = "BC카드",
                color = woowacourse.payments.ui.theme.BC,
            )

        BankType.SHINHAN ->
            CardCompanyUiModel.create(
                name = "신한카드",
                color = woowacourse.payments.ui.theme.SHINHAN,
            )

        BankType.KAKAOBANK ->
            CardCompanyUiModel.create(
                name = "카카오뱅크",
                color = woowacourse.payments.ui.theme.KAKAO,
            )

        BankType.HYUNDAI ->
            CardCompanyUiModel.create(
                name = "현대카드",
                color = woowacourse.payments.ui.theme.HYUNDAI,
            )

        BankType.WOORI ->
            CardCompanyUiModel.create(
                name = "우리카드",
                color = woowacourse.payments.ui.theme.WOORI,
            )

        BankType.LOTTE ->
            CardCompanyUiModel.create(
                name = "롯데카드",
                color = woowacourse.payments.ui.theme.LOTTE,
            )

        BankType.HANA ->
            CardCompanyUiModel.create(
                name = "하나카드",
                color = woowacourse.payments.ui.theme.HANA,
            )

        BankType.KB ->
            CardCompanyUiModel.create(
                name = "KB카드",
                color = woowacourse.payments.ui.theme.KB,
            )

        BankType.NOT_SELECTED ->
            CardCompanyUiModel.create(
                name = "",
                color = woowacourse.payments.ui.theme.GRAY,
            )
    }
