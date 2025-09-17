package woowacourse.payments.ui.card.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Bank
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.color
import woowacourse.payments.ui.card.component.PaymentCard
import woowacourse.payments.ui.card.register.component.BankSelectBottomSheet
import woowacourse.payments.ui.card.register.component.CardExpirationDateTextField
import woowacourse.payments.ui.card.register.component.CardHolderNameTextField
import woowacourse.payments.ui.card.register.component.CardNumberTextField
import woowacourse.payments.ui.card.register.component.CardPasswordTextField
import woowacourse.payments.ui.card.register.component.NewCardTopBar
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun RegisterCardScreen(
    onCardSaved: (newCardUiModel: CardUiModel) -> Unit,
    onBackClick: () -> Unit,
) {
    val stateHolder = remember { RegisterCardStateHolder(onCardSaved) }
    val uiState = stateHolder.uiState

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = { stateHolder.saveCard() },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .padding(top = 14.dp)
                        .align(Alignment.CenterHorizontally),
                bankName = uiState.selectedBank?.name,
                backgroundColor = uiState.selectedBank?.color() ?: Color(0xFF333333),
            )
            CardNumberTextField(
                value = uiState.cardNumber,
                onValueChange = { stateHolder.updateCardNumber(it) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
            )
            CardExpirationDateTextField(
                value = uiState.expirationDate,
                onValueChange = { stateHolder.updateExpirationDate(it) },
                modifier =
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(0.5f)
                        .defaultMinSize(minWidth = 200.dp),
            )
            CardHolderNameTextField(
                value = uiState.cardHolderName,
                onValueChange = { stateHolder.updateCardHolderName(it) },
                modifier =
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
            )
            CardPasswordTextField(
                value = uiState.password,
                onValueChange = { stateHolder.updatePassword(it) },
                modifier =
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(0.5f)
                        .defaultMinSize(minWidth = 200.dp),
            )
        }

        if (uiState.showBottomSheet) {
            val banks =
                listOf(
                    Bank(BankType.BC, "BC카드"),
                    Bank(BankType.SHINHAN, "신한카드"),
                    Bank(BankType.KAKAO, "카카오뱅크"),
                    Bank(BankType.HYUNDAI, "현대카드"),
                    Bank(BankType.WOORI, "우리카드"),
                    Bank(BankType.LOTTE, "롯데카드"),
                    Bank(BankType.HANA, "하나카드"),
                    Bank(BankType.KB, "국민카드"),
                )
            BankSelectBottomSheet(
                banks = banks,
                onBankSelected = { stateHolder.updateSelectedBank(it) },
            )
        }
    }
}
