package woowacourse.payments.ui.screen.cards

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.component.CardExpirationDateUiModel
import woowacourse.payments.ui.component.CardNumberUiModel
import woowacourse.payments.ui.component.CardRegistrationButton
import woowacourse.payments.ui.component.CardholderNameUiModel
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.PaymentCardUiModel
import woowacourse.payments.ui.extension.getParcelableExtraCompat
import woowacourse.payments.ui.screen.registration.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(viewModel: CardsViewModel = rememberCardsScreenViewModel()) {
    val context = LocalContext.current
    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode != Activity.RESULT_OK) return@rememberLauncherForActivityResult
            activityResult.data?.let { data ->
                data
                    .getParcelableExtraCompat<PaymentCardUiModel>(CardRegistrationActivity.EXTRA_NEW_CARD)
                    ?.let(viewModel::addCard)
            }
        }

    LaunchedEffect(viewModel.uiEvent) {
        when (viewModel.uiEvent) {
            is CardsScreenUiEvent.RegisteredCard ->
                Toast
                    .makeText(
                        context,
                        R.string.card_registration_screen_card_registered_message,
                        Toast.LENGTH_SHORT,
                    ).show()

            null -> Unit
        }
    }

    Scaffold(
        topBar = {
            CardsTopAppBar(
                onRegistrationButtonClick = {
                    cardAddLauncher.launch(CardRegistrationActivity.newIntent(context))
                },
                isRegistrationButtonEnabled = viewModel.uiState is CardsUiState.MULTIPLE,
            )
        },
    ) { innerPadding ->
        CardsScreenContent(
            cardsUiState = viewModel.uiState,
            onRegistrationButtonClick = {
                cardAddLauncher.launch(CardRegistrationActivity.newIntent(context))
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun CardsScreenContent(
    cardsUiState: CardsUiState,
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        when (cardsUiState) {
            is CardsUiState.EMPTY -> CardsEmptyContent(onRegistrationButtonClick)
            is CardsUiState.MULTIPLE -> CardsMultipleContent(cardsUiState.cards)
            is CardsUiState.SINGLE ->
                CardsSingleContent(cardsUiState.card, onRegistrationButtonClick)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun CardsEmptyContent(onRegistrationButtonClick: () -> Unit) {
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "새로운 카드를 등록해주세요",
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
    )
    CardRegistrationButton(onClick = onRegistrationButtonClick)
}

@Composable
private fun CardsSingleContent(
    card: PaymentCardUiModel,
    onRegistrationButtonClick: () -> Unit,
) {
    PaymentCard(paymentCardUiModel = card)
    CardRegistrationButton(onClick = onRegistrationButtonClick)
}

@Composable
private fun CardsMultipleContent(cards: List<PaymentCardUiModel>) {
    cards.forEach { card -> PaymentCard(paymentCardUiModel = card) }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) cards: CardsUiState,
) {
    AndroidpaymentsTheme {
        CardsScreen(viewModel = CardsViewModel(cards))
    }
}

private class CardsScreenPreviewParameterProvider : PreviewParameterProvider<CardsUiState> {
    private val cards =
        List(3) {
            PaymentCardUiModel(
                number = CardNumberUiModel("1234567812345678"),
                expirationDate = CardExpirationDateUiModel("0301"),
                cardholderName = CardholderNameUiModel("DICE"),
            )
        }

    override val values =
        sequenceOf(
            CardsUiState.EMPTY,
            CardsUiState.SINGLE(cards.first()),
            CardsUiState.MULTIPLE(cards),
        )
}
