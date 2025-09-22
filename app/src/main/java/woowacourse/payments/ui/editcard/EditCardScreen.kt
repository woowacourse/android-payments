package woowacourse.payments.ui.editcard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import woowacourse.payments.ui.registercard.toYearMonth
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toBankType
import java.time.YearMonth

@Composable
fun EditCardScreen(
    card: Card,
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val stateHolder = rememberSaveable { RegisterCardStateHolder() }
    LaunchedEffect(Unit) {
        stateHolder.setupRegisteredCardInfo(card)
    }

    Scaffold(topBar = {
        CardTopBar(
            stringResource(R.string.card_edit_top_bar_title),
            onBackClick = onBackClick,
            onSaveClick = {
                val result =
                    Card.create(
                        cardNumber = stateHolder.cardNumber,
                        expiryDate = stateHolder.expiryDate.toYearMonth(),
                        cardOwner = stateHolder.cardOwner,
                        password = stateHolder.password,
                        bankType = stateHolder.selectedBankViewType.toBankType(),
                    )

                result
                    .onSuccess { card ->
                        Toast
                            .makeText(
                                context,
                                context.getString(R.string.card_register_complete_message),
                                Toast.LENGTH_SHORT,
                            ).show()
                        onSaveClick(card)
                    }.onFailure {
                        Toast
                            .makeText(
                                context,
                                context.getString(R.string.card_info_invalid_message),
                                Toast.LENGTH_SHORT,
                            ).show()
                    }
            },
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
            bankViewType = stateHolder.selectedBankViewType,
            modifier =
                Modifier
                    .padding(top = 14.dp, bottom = 40.dp)
                    .align(Alignment.CenterHorizontally),
            card = card,
        )
        CardNumberInputField(
            text = stateHolder.cardNumber,
            onValueChange = { newText -> stateHolder.onCardNumberChange(newText) },
            isError = stateHolder.isCardNumberError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExpiryDateInputField(
            text = stateHolder.expiryDate,
            onValueChange = { newText -> stateHolder.onExpiryDateChange(newText) },
            isError = stateHolder.isExpiryDateError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        CardOwnerInputField(
            text = stateHolder.cardOwner,
            onValueChange = { newText -> stateHolder.onCardOwnerChange(newText) },
            isError = stateHolder.isCardOwnerError,
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordInputField(
            text = stateHolder.password,
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
