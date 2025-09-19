package woowacourse.payments.ui.view.new

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.state.CardCompanyState
import woowacourse.payments.ui.state.CardState

@Parcelize
data class NewCardUiState(
    val number: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val company: CardCompanyState = CardCompanyState.Empty,
): Parcelable {
    val cardState
        get() =
            when (company) {
                CardCompanyState.Empty -> CardState.Pending
                is CardCompanyState.Selected ->
                    CardState.Registered(
                        Card(
                            number = number,
                            expireDate = expireDate,
                            ownerName = ownerName,
                            password = password,
                            company = company.company,
                        ),
                    )
            }

    fun toDomain(): Card =
        Card(
            number = number,
            expireDate = expireDate,
            ownerName = ownerName,
            password = password,
            company =
                (company as? CardCompanyState.Selected)?.company
                    ?: throw IllegalStateException(CARD_COMPANY_NOT_SELECTED),
        )

    companion object {
        private const val CARD_COMPANY_NOT_SELECTED = "카드사가 선택되지 않았습니다."
    }
}
