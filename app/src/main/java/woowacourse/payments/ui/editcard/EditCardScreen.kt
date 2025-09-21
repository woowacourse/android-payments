package woowacourse.payments.ui.editcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.CardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.registercard.RegisterCardStateHolder
import woowacourse.payments.ui.registercard.component.CardNumberInputField
import woowacourse.payments.ui.registercard.component.CardOwnerInputField
import woowacourse.payments.ui.registercard.component.ExpiryDateInputField
import woowacourse.payments.ui.registercard.component.PasswordInputField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toBankViewType
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun EditCardScreen(
    card: Card,
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateHolder = rememberSaveable { RegisterCardStateHolder() }
    Scaffold(topBar = {
        CardTopBar(
            stringResource(R.string.card_edit_top_bar_title),
            onBackClick = onBackClick,
            onSaveClick = { onSaveClick(card) },
            modifier = modifier,
        )
    }) { innerPadding ->
        EditCardContent(
            card = card,
            innerPadding = innerPadding,
            stateHolder = stateHolder,
            modifier = modifier,
        )
    }
}

@Composable
private fun EditCardContent(
    card: Card,
    innerPadding: PaddingValues,
    stateHolder: RegisterCardStateHolder,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            bankViewType = card.bankType.toBankViewType(),
            modifier =
                Modifier
                    .padding(top = 14.dp, bottom = 40.dp)
                    .align(Alignment.CenterHorizontally),
            card = card,
        )
        CardNumberInputField(
            text = card.cardNumber,
            onValueChange = { newText -> stateHolder.onCardNumberChange(newText) },
            isError = stateHolder.isCardNumberError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExpiryDateInputField(
            text = card.expiryDate.format(DateTimeFormatter.ofPattern("MM/yy")),
            onValueChange = { newText -> stateHolder.onExpiryDateChange(newText) },
            isError = stateHolder.isExpiryDateError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        CardOwnerInputField(
            text = card.cardOwner ?: "",
            onValueChange = { newText -> stateHolder.onCardOwnerChange(newText) },
            isError = stateHolder.isCardOwnerError,
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordInputField(
            text = card.password,
            onValueChange = { newText -> stateHolder.onPasswordChange(newText) },
            isError = stateHolder.isPasswordError,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EditCardContentPreview() {
    val card =
        Card.create(
            cardNumber = "1234567812345678",
            expiryDate = YearMonth.of(2095, 12),
            cardOwner = "쥐돌킹",
            password = "1234",
            bankType = BankType.BC,
        )
    EditCardContent(
        card = card.getOrNull()!!,
        innerPadding = PaddingValues(),
        stateHolder = RegisterCardStateHolder(),
    )
}

@Preview(showBackground = true)
@Composable
private fun EditCardScreenPreview() {
    val card =
        Card.create(
            cardNumber = "1234567812345678",
            expiryDate = YearMonth.of(2095, 12),
            cardOwner = "쥐돌킹",
            password = "1234",
            bankType = BankType.BC,
        )
    AndroidpaymentsTheme {
        EditCardScreen(
            card = card.getOrNull()!!,
            onBackClick = { },
            onSaveClick = { },
        )
    }
}
