package woowacourse.payments

import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.ui.uimodel.CardInfoUiState

val cards = mutableStateListOf(
    CardInfoUiState(
        _cardNumber = "1234123412341234",
        _password = "1234",
        _ownerName = "홍길동홍길동홍길동홍길동홍길동홍길동",
        _expireDate = "1225"
    ),
    CardInfoUiState(
        _cardNumber = "1234123412341234",
        _password = "1234",
        _ownerName = "홍길동",
        _expireDate = "1225"
    ),
    CardInfoUiState(
        _cardNumber = "1234123412341234",
        _password = "1234",
        _ownerName = "홍길동",
        _expireDate = "1225"
    ),
)
