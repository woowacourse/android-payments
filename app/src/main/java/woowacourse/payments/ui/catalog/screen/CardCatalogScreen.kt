package woowacourse.payments.ui.catalog.screen

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.getValue
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.catalog.CardCatalogActivity.Companion.PAYMENT_CARD_UI_MODEL_KEY
import woowacourse.payments.ui.catalog.CardUiState
import woowacourse.payments.ui.catalog.CardViewModel
import woowacourse.payments.ui.catalog.component.AddCardButton
import woowacourse.payments.ui.catalog.component.CardCatalogTopAppBar
import woowacourse.payments.ui.common.component.PaymentCardField
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.common.showToast
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.payments.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardCatalogScreen(cardViewModel: CardViewModel = CardViewModel()) {
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
                context.showToast(context.getString(R.string.card_catalog_screen_add_canceled))
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
            modifier = Modifier.padding(innerPadding),
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
                    modifier = modifier,
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
    Text(
        text = stringResource(R.string.CARD_CATALOG_SCREEN_REGISTRATION_NEW_CARD),
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        modifier = modifier,
    )

    AddCardButton(
        onClick = { onAddNewCardClick() },
        modifier = Modifier.padding(top = 32.dp),
    )
}

@Composable
private fun SingleCardCatalogScreenContent(
    paymentCardUiModel: PaymentCardUiModel,
    onAddNewCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PaymentCardField(paymentCardUiModel = paymentCardUiModel, modifier = modifier)

    AddCardButton(
        onClick = { onAddNewCardClick() },
        modifier = Modifier.padding(top = 32.dp),
    )
}

@Composable
private fun MultipleCardCatalogScreenContent(
    paymentCardUiModels: List<PaymentCardUiModel>,
    modifier: Modifier = Modifier,
) {
    paymentCardUiModels.forEach { paymentCard ->
        Spacer(modifier = Modifier.height(36.dp))
        PaymentCardField(paymentCardUiModel = paymentCard, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun CardCatalogScreenPreView() {
    AndroidpaymentsTheme {
        CardCatalogScreen(
            cardViewModel =
                CardViewModel(
                    CardUiState.Single(
                        PaymentCardUiModel(
                            number = "1234123412341234",
                            expirationDate = "1234",
                            cardholderName = "CREW",
                        ),
                    ),
                ),
        )
    }
}
