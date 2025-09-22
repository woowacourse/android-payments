package woowacourse.payments.ui.registercard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
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
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.cards.component.SelectBankBottomSheet
import woowacourse.payments.ui.component.CardTextFields
import woowacourse.payments.ui.component.CardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toBankType
import java.time.YearMonth

@Composable
fun CardRegisterScreen(
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val stateHolder = rememberSaveable { CardTextFieldStateHolder() }

    Scaffold(
        topBar = {
            CardTopBar(
                title = stringResource(R.string.card_register_top_bar_title),
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
        },
        content = { innerPadding ->
            CardRegisterContent(
                innerPadding = innerPadding,
                stateHolder = stateHolder,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        },
    )
}

fun String.toYearMonth(): YearMonth? {
    val yearOffset = 2000
    if (length != 4) return null
    val year = substring(2, 4).toIntOrNull()
    val month = substring(0, 2).toIntOrNull()
    if (month !in 1..12) return null
    return if (year == null || month == null) {
        null
    } else {
        YearMonth.of(yearOffset + year, month)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardRegisterContent(
    innerPadding: PaddingValues,
    stateHolder: CardTextFieldStateHolder,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )

    LaunchedEffect(stateHolder.selectedBankViewType) {
        if (stateHolder.selectedBankViewType != BankViewType.NONE) {
            modalBottomSheetState.hide()
        }
    }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            bankViewType = stateHolder.selectedBankViewType,
            modifier =
                Modifier
                    .padding(top = 14.dp, bottom = 40.dp)
                    .align(Alignment.CenterHorizontally),
        )
        CardTextFields(stateHolder = stateHolder)
    }
    if (stateHolder.selectedBankViewType == BankViewType.NONE) {
        SelectBankBottomSheet(
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            onBankSelectClick = {
                stateHolder.onSelectedBankViewTypeChange(it)
            },
            sheetState = modalBottomSheetState,
            onDismissRequest = onBackClick,
        )
    }
}

@Preview
@Composable
private fun ShowCardRegisterScreenPreview() {
    AndroidpaymentsTheme {
        CardRegisterScreen(
            onBackClick = { },
            onSaveClick = { },
        )
    }
}
