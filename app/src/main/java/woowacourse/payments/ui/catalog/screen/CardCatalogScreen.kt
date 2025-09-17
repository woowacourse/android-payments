package woowacourse.payments.ui.catalog.screen

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import woowacourse.payments.R
import woowacourse.payments.ui.catalog.CardCatalogActivity.Companion.PAYMENT_CARD_UI_MODEL_KEY
import woowacourse.payments.ui.catalog.CardUiState
import woowacourse.payments.ui.catalog.CardViewModel
import woowacourse.payments.ui.catalog.component.AddCardButton
import woowacourse.payments.ui.catalog.component.CardCatalogTopAppBar
import woowacourse.payments.ui.common.component.PaymentCardField
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.common.showShortToast
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.payments.CardRegistrationActivity
import woowacourse.payments.ui.payments.model.BankUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardCatalogScreen(
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel = CardViewModel(),
) {
    val context = LocalContext.current
    val uiState: CardUiState by cardViewModel.cardUiState.observeAsState(CardUiState.Empty)

    val cardCatalogLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val paymentCardUiModel =
                    activityResult.data?.getParcelableExtraCompat<PaymentCardUiModel>(
                        PAYMENT_CARD_UI_MODEL_KEY,
                    )
                paymentCardUiModel?.let { cardViewModel.addCard(it) }
            } else {
                context.showShortToast(context.getString(R.string.card_catalog_screen_add_canceled))
            }
        }
    Scaffold(
        topBar = {
            CardCatalogTopAppBar(
                isAddButtonVisible = uiState.isAddCardButtonVisible,
                onCardAddClick = {
                    val intent = CardRegistrationActivity.newIntent(context)
                    cardCatalogLauncher.launch(intent)
                },
            )
        },
    ) { innerPadding ->
        CardCatalogScreenContent(
            uiState = uiState,
            modifier = modifier.padding(innerPadding),
            onAddNewCardClick = {
                val intent = CardRegistrationActivity.newIntent(context)
                cardCatalogLauncher.launch(intent)
            },
        )
    }
}

@Composable
fun CardCatalogScreenContent(
    uiState: CardUiState,
    modifier: Modifier = Modifier,
    onAddNewCardClick: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            CardUiState.Empty ->
                EmptyCardCatalogScreenContent(
                    onAddNewCardClick = onAddNewCardClick,
                )

            is CardUiState.Single ->
                SingleCardCatalogScreenContent(
                    paymentCardUiModel = uiState.paymentCard,
                    onAddNewCardClick = onAddNewCardClick,
                )

            is CardUiState.Multiple ->
                MultipleCardCatalogScreenContent(
                    paymentCardUiModels = uiState.paymentCards,
                )
        }
    }
}

@Composable
private fun EmptyCardCatalogScreenContent(
    onAddNewCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.card_catalog_screen_registration_new_card),
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 32.dp),
        )

        AddCardButton(
            onClick = onAddNewCardClick,
            modifier = Modifier.padding(top = 32.dp),
        )
    }
}

@Composable
private fun SingleCardCatalogScreenContent(
    paymentCardUiModel: PaymentCardUiModel,
    onAddNewCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        PaymentCardField(paymentCardUiModel = paymentCardUiModel, modifier = Modifier)

        AddCardButton(
            onClick = { onAddNewCardClick() },
            modifier = Modifier.padding(top = 32.dp),
        )
    }
}

@Composable
private fun MultipleCardCatalogScreenContent(
    paymentCardUiModels: ImmutableList<PaymentCardUiModel>,
    modifier: Modifier = Modifier,
) {
    paymentCardUiModels.forEach { paymentCard ->
        Spacer(modifier = Modifier.height(36.dp))
        PaymentCardField(paymentCardUiModel = paymentCard, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCatalogScreenPreView(
    @PreviewParameter(CardCatalogScreenPreviewParameterProvider::class) cardUiState: CardUiState,
) {
    AndroidpaymentsTheme {
        CardCatalogScreen(
            cardViewModel = CardViewModel(cardUiState),
        )
    }
}

private class CardCatalogScreenPreviewParameterProvider : PreviewParameterProvider<CardUiState> {
    override val values: Sequence<CardUiState> =
        sequenceOf(
            CardUiState.Empty,
            CardUiState.Single(
                PaymentCardUiModel(
                    number = "1234123412341234",
                    expirationDate = "1234",
                    cardholderName = "CREW",
                    bankUiState = BankUiState.KB,
                ),
            ),
            CardUiState.Multiple(
                persistentListOf(
                    PaymentCardUiModel(
                        number = "1234123412341234",
                        expirationDate = "1234",
                        cardholderName = "CREW",
                        bankUiState = BankUiState.KB,
                    ),
                    PaymentCardUiModel(
                        number = "1234123412341231",
                        expirationDate = "1234",
                        cardholderName = "CREW",
                        bankUiState = BankUiState.SHINHAN,
                    ),
                ),
            ),
        )
}
