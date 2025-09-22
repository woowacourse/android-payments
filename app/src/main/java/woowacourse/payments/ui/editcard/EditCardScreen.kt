package woowacourse.payments.ui.editcard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import woowacourse.payments.ui.component.CardInputFields
import woowacourse.payments.ui.component.CardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.registercard.CardInputFieldStateHolder
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toBankType
import woowacourse.payments.ui.toYearMonth
import java.time.YearMonth

@Composable
fun EditCardScreen(
    card: Card,
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val stateHolder = rememberSaveable { CardInputFieldStateHolder() }
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
    stateHolder: CardInputFieldStateHolder,
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
        CardInputFields(stateHolder = stateHolder)
    }
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
