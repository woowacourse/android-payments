package woowacourse.payments

import java.time.YearMonth

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        number = number,
        expiredDate = expiredDate.displayString,
        holder = holder ?: "",
        password = password,
        bankType = bankType,
    )

private val YearMonth.displayString: String
    get() {
        val month: String = monthValue.toString().padStart(2, '0')
        val year: String = (year % 100).toString().padStart(2, '0')
        return "$month$year"
    }

fun CardUiModel.toDomain(): Card =
    Card(
        bankType = bankType ?: throw IllegalArgumentException("은행이 선택되지 않았습니다."),
        number = number,
        expiredDate = expiredDate,
        password = password,
        holder = holder,
    )
