package woowacourse.payments.view

import YearMonthParser
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import java.time.YearMonth

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        number = number.value,
        expiredDate = expiredDate.displayString,
        holder = holder ?: "",
        password = password.value,
        bankType = bankType.toUiModel(),
    )

fun CardUiModel.toDomain(): Card =
    Card(
        bankType = bankType?.toDomain() ?: throw IllegalArgumentException("은행이 선택되지 않았습니다."),
        number = CardNumber(number),
        expiredDate = YearMonthParser.parse(expiredDate),
        password = CardPassword(password),
        holder = holder,
    )

fun BankType.toUiModel(): BankTypeUiModel =
    when (this) {
        BankType.BC -> BankTypeUiModel.BC
        BankType.SHINHAN -> BankTypeUiModel.SHINHAN
        BankType.KAKAO -> BankTypeUiModel.KAKAO
        BankType.HYUNDAI -> BankTypeUiModel.HYUNDAI
        BankType.WOORI -> BankTypeUiModel.WOORI
        BankType.LOTTE -> BankTypeUiModel.LOTTE
        BankType.HANA -> BankTypeUiModel.HANA
        BankType.KB -> BankTypeUiModel.KB
    }

fun BankTypeUiModel.toDomain(): BankType =
    when (this) {
        BankTypeUiModel.BC -> BankType.BC
        BankTypeUiModel.SHINHAN -> BankType.SHINHAN
        BankTypeUiModel.KAKAO -> BankType.KAKAO
        BankTypeUiModel.HYUNDAI -> BankType.HYUNDAI
        BankTypeUiModel.WOORI -> BankType.WOORI
        BankTypeUiModel.LOTTE -> BankType.LOTTE
        BankTypeUiModel.HANA -> BankType.HANA
        BankTypeUiModel.KB -> BankType.KB
    }

private val YearMonth.displayString: String
    get() {
        val month: String = monthValue.toString().padStart(2, '0')
        val year: String = (year % 100).toString().padStart(2, '0')
        return "$month$year"
    }
