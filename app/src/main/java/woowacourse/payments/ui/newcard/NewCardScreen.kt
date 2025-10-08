package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.component.CardCompanySelectBottomSheet
import woowacourse.payments.ui.component.CardExpiryDate
import woowacourse.payments.ui.component.CardHolderName
import woowacourse.payments.ui.component.CardNumber
import woowacourse.payments.ui.component.CardPassword
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.RegisteredCard
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun NewCardScreen(
    existingCard: CardUiModel? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (CardUiModel) -> Unit = {},
) {
    val isEditMode = existingCard != null
    var cardUiModel by
        rememberSaveable {
            mutableStateOf(
                CardUiModel(
                    cardNumber = existingCard?.cardNumber ?: "",
                    cardHolderName = existingCard?.cardHolderName ?: "",
                    cardExpiryDate = existingCard?.cardExpiryDate ?: "",
                    cardPassword = existingCard?.cardPassword ?: "",
                    cardCompanyUiModel = existingCard?.cardCompanyUiModel,
                ),
            )
        }

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (existingCard == null) {
            showBottomSheet = true
        }
    }

    val isChanges =
        existingCard?.isDifferentFrom(
            cardUiModel.cardNumber,
            cardUiModel.cardExpiryDate,
            cardUiModel.cardHolderName,
            cardUiModel.cardPassword,
            cardUiModel.cardCompanyUiModel,
        ) == true

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                title =
                    if (isEditMode) {
                        stringResource(R.string.card_edit)
                    } else {
                        stringResource(R.string.card_add)
                    },
                onBackClick = { onBackClick() },
                onSaveClick = {
                    if (isEditMode) {
                        if (isChanges) {
                            onSaveClick(
                                CardUiModel(
                                    cardNumber = cardUiModel.cardNumber,
                                    cardHolderName = cardUiModel.cardHolderName,
                                    cardExpiryDate = cardUiModel.cardExpiryDate,
                                    cardPassword = cardUiModel.cardPassword,
                                    cardCompanyUiModel = cardUiModel.cardCompanyUiModel,
                                ),
                            )
                        }
                    } else {
                        onSaveClick(
                            CardUiModel(
                                cardNumber = cardUiModel.cardNumber,
                                cardHolderName = cardUiModel.cardHolderName,
                                cardExpiryDate = cardUiModel.cardExpiryDate,
                                cardPassword = cardUiModel.cardPassword,
                                cardCompanyUiModel = cardUiModel.cardCompanyUiModel,
                            ),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            if (isEditMode) {
                RegisteredCard(
                    cardUiModel = cardUiModel,
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally),
                    onClick = { showBottomSheet = true },
                )
            } else {
                PaymentCard(
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally),
                    cardUiModel = cardUiModel,
                    onCompanyClick = { showBottomSheet = true },
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            CardNumber(
                value = cardUiModel.cardNumber,
                onValueChange = { cardUiModel = cardUiModel.copy(cardNumber = it) },
                modifier =
                    Modifier
                        .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(30.dp))
            CardExpiryDate(
                value = cardUiModel.cardExpiryDate,
                onValueChange = { cardUiModel = cardUiModel.copy(cardExpiryDate = it) },
                modifier = Modifier.fillMaxWidth(0.5f),
            )

            Spacer(modifier = Modifier.height(30.dp))
            CardHolderName(
                value = cardUiModel.cardHolderName,
                onValueChange = { cardUiModel = cardUiModel.copy(cardHolderName = it) },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(10.dp))
            CardPassword(
                value = cardUiModel.cardPassword,
                onValueChange = { cardUiModel = cardUiModel.copy(cardPassword = it) },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
    if (showBottomSheet) {
        CardCompanySelectBottomSheet(
            onCompanyClick = { company ->
                cardUiModel = cardUiModel.copy(cardCompanyUiModel = company)
                showBottomSheet = false
            },
            onDismissRequest = {
                showBottomSheet = false
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
