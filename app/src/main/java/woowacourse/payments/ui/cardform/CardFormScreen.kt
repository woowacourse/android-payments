package woowacourse.payments.ui.cardform

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
import woowacourse.payments.ui.CardInputFieldStateHolder
import woowacourse.payments.ui.cardform.component.CardInputFields
import woowacourse.payments.ui.cardform.component.CardTopBar
import woowacourse.payments.ui.cards.component.SelectBankBottomSheet
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toBankType
import woowacourse.payments.ui.toYearMonth

@Composable
fun CardFormScreen(
    card: Card?,
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val stateHolder =
        rememberSaveable {
            CardInputFieldStateHolder().apply {
                card?.let { setupRegisteredCardInfo(it) }
            }
        }

    Scaffold(
        topBar = {
            CardTopBar(
                title =
                    if (card == null) {
                        stringResource(R.string.card_register_top_bar_title)
                    } else {
                        stringResource(
                            R.string.card_edit_top_bar_title,
                        )
                    },
                onBackClick = onBackClick,
                onSaveClick = {
                    val result =
                        Card.create(
                            id = card?.id,
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
                canSave = stateHolder.canSave,
            )
        },
        content = { innerPadding ->
            CardFormContent(
                innerPadding = innerPadding,
                stateHolder = stateHolder,
                onBackClick = onBackClick,
                modifier = modifier,
                card = card,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardFormContent(
    card: Card?,
    innerPadding: PaddingValues,
    stateHolder: CardInputFieldStateHolder,
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
            card = card,
        )
        CardInputFields(stateHolder = stateHolder)
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
private fun CardFormScreenPreview() {
    AndroidpaymentsTheme {
        CardFormScreen(
            onBackClick = { },
            onSaveClick = { },
            card = null,
        )
    }
}
