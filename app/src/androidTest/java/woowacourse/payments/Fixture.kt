package woowacourse.payments

import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.ui.uimodel.CardInfoUiModel
import woowacourse.payments.ui.uimodel.CardInfoUiState

val cards =
    mutableStateListOf(
        CardInfoUiState(
            CardInfoUiModel(
                cardNumber = "1234123412341234",
                password = "1234",
                ownerName = "홍길동홍길동홍길동홍길동홍길동홍길동",
                expireDate = "1225",
            ),
        ),
        CardInfoUiState(
            CardInfoUiModel(
                cardNumber = "1234123412341234",
                password = "1234",
                ownerName = "홍길동",
                expireDate = "1225",
            ),
        ),
        CardInfoUiState(
            CardInfoUiModel(
                cardNumber = "1234123412341234",
                password = "1234",
                ownerName = "홍길동",
                expireDate = "1225",
            ),
        ),
    )
