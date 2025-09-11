package woowacourse.payments.ui.screen.cards

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.component.CardRegistrationButton
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.extension.getParcelableExtraCompat
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.screen.registration.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(viewModel: CardsScreenViewModel = rememberCardsScreenViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.observeAsState(CardsUiState.EMPTY)
    val uiEvent by viewModel.uiEvent.observeAsState(CardsScreenUiEvent.None)
    val cardAddLauncher = rememberCardAddLauncher(viewModel)

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is CardsScreenUiEvent.None -> Unit
            is CardsScreenUiEvent.RegisteredCard -> {
                val resId = R.string.cards_screen_card_registered_message
                Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            CardsTopAppBar(
                onRegistrationButtonClick = {
                    val newIntent = CardRegistrationActivity.newIntent(context)
                    cardAddLauncher.launch(newIntent)
                },
                showRegistrationButton = uiState is CardsUiState.MULTIPLE,
            )
        },
    ) { innerPadding ->
        CardsScreenContent(
            cardsUiState = uiState,
            onRegistrationButtonClick = {
                val newIntent = CardRegistrationActivity.newIntent(context)
                cardAddLauncher.launch(newIntent)
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun rememberCardAddLauncher(viewModel: CardsScreenViewModel) =
    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode != Activity.RESULT_OK) return@rememberLauncherForActivityResult
        result.data
            ?.getParcelableExtraCompat<PaymentCardUiModel>(CardRegistrationActivity.EXTRA_NEW_CARD)
            ?.let(viewModel::addCard)
    }

@Composable
private fun CardsScreenContent(
    cardsUiState: CardsUiState,
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        when (cardsUiState) {
            is CardsUiState.EMPTY -> CardsEmptyContent(onRegistrationButtonClick)
            is CardsUiState.MULTIPLE -> CardsMultipleContent(cardsUiState.cards)
            is CardsUiState.SINGLE ->
                CardsSingleContent(cardsUiState.card, onRegistrationButtonClick)
        }
    }
}

@Composable
private fun CardsEmptyContent(
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.cards_screen_registration_message),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
        )
        Spacer(modifier = Modifier.height(20.dp))
        CardRegistrationButton(onClick = onRegistrationButtonClick)
    }
}

@Composable
private fun CardsSingleContent(
    card: PaymentCardUiModel,
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PaymentCard(paymentCardUiModel = card)
        CardRegistrationButton(onClick = onRegistrationButtonClick)
    }
}

@Composable
private fun CardsMultipleContent(
    cards: List<PaymentCardUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        cards.forEach { card -> PaymentCard(paymentCardUiModel = card) }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) cards: CardsUiState,
) {
    AndroidpaymentsTheme {
        CardsScreen(viewModel = CardsScreenViewModel(cards))
    }
}

private class CardsScreenPreviewParameterProvider : PreviewParameterProvider<CardsUiState> {
    override val values =
        sequenceOf(
            CardsUiState.EMPTY,
            CardsUiState.SINGLE(DUMMY_CARDS.first()),
            CardsUiState.MULTIPLE(DUMMY_CARDS),
        )

    companion object {
        private val DUMMY_CARDS =
            List(3) {
                PaymentCardUiModel(
                    number = CardNumberUiModel("1234567812345678"),
                    expirationDate = CardExpirationDateUiModel("0301"),
                    cardholderName = CardholderNameUiModel("DICE", 30),
                )
            }
    }
}
