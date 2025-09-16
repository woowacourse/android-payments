package woowacourse.payments.ui.util

import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.CardCompanyUiModel

fun BankType.toCardCompanyUiModel(): CardCompanyUiModel =
    when (this) {
        BankType.BC ->
            CardCompanyUiModel.create(
                name = "BC카드",
                color = woowacourse.payments.ui.theme.BC,
                logoRes = R.drawable.img_bc,
            )

        BankType.SHINHAN ->
            CardCompanyUiModel.create(
                name = "신한카드",
                color = woowacourse.payments.ui.theme.SHINHAN,
                logoRes = R.drawable.img_shinhan,
            )

        BankType.KAKAOBANK ->
            CardCompanyUiModel.create(
                name = "카카오뱅크",
                color = woowacourse.payments.ui.theme.KAKAO,
                logoRes = R.drawable.img_kakaobank,
            )

        BankType.HYUNDAI ->
            CardCompanyUiModel.create(
                name = "현대카드",
                color = woowacourse.payments.ui.theme.HYUNDAI,
                logoRes = R.drawable.img_hyundai,
            )

        BankType.WOORI ->
            CardCompanyUiModel.create(
                name = "우리카드",
                color = woowacourse.payments.ui.theme.WOORI,
                logoRes = R.drawable.img_woori,
            )

        BankType.LOTTE ->
            CardCompanyUiModel.create(
                name = "롯데카드",
                color = woowacourse.payments.ui.theme.LOTTE,
                logoRes = R.drawable.img_lotte,
            )

        BankType.HANA ->
            CardCompanyUiModel.create(
                name = "하나카드",
                color = woowacourse.payments.ui.theme.HANA,
                logoRes = R.drawable.img_hana,
            )

        BankType.KB ->
            CardCompanyUiModel.create(
                name = "KB카드",
                color = woowacourse.payments.ui.theme.KB,
                logoRes = R.drawable.img_kb,
            )

        BankType.NOT_SELECTED ->
            CardCompanyUiModel.create(
                name = "",
                color = woowacourse.payments.ui.theme.GRAY,
                logoRes = R.drawable.ic_not_visible,
            )
    }
