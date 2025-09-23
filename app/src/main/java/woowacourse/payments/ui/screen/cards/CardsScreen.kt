package woowacourse.payments.ui.screen.cards

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
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
import woowacourse.payments.ui.model.BankTypeUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.PaymentCardsUiModel
import woowacourse.payments.ui.model.toBankName
import woowacourse.payments.ui.screen.registration.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    onRegisterCardClick: (ManagedActivityResultLauncher<Intent, ActivityResult>) -> Unit,
    onEditCardClick: (PaymentCardUiModel, ManagedActivityResultLauncher<Intent, ActivityResult>) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardsScreenViewModel = rememberCardsScreenViewModel(),
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.observeAsState().value ?: return
    val uiEvent = viewModel.uiEvent.observeAsState().value
    val launcher = rememberCardAddLauncher(viewModel::addCard)

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is CardsScreenUiEvent.RegisteredCard -> {
                val resId = R.string.cards_screen_card_registered_message
                Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
            }

            null -> Unit
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopAppBar(
                onRegistrationButtonClick = { onRegisterCardClick(launcher) },
                showRegistrationButton = uiState is CardsUiState.MULTIPLE,
            )
        },
    ) { innerPadding ->
        CardsScreenContent(
            cardsUiState = uiState,
            onRegistrationButtonClick = { onRegisterCardClick(launcher) },
            onCardClick = { card -> onEditCardClick(card, launcher) },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun rememberCardAddLauncher(onCardAdded: (PaymentCardUiModel) -> Unit) =
    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode != Activity.RESULT_OK) return@rememberLauncherForActivityResult
        result.data
            ?.getParcelableExtraCompat<PaymentCardUiModel>(CardRegistrationActivity.EXTRA_NEW_CARD)
            ?.let(onCardAdded)
    }

@Composable
private fun CardsScreenContent(
    cardsUiState: CardsUiState,
    onRegistrationButtonClick: () -> Unit,
    onCardClick: (PaymentCardUiModel) -> Unit,
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
            is CardsUiState.EMPTY ->
                CardsEmptyContent(onRegistrationButtonClick)

            is CardsUiState.SINGLE ->
                CardsSingleContent(
                    card = cardsUiState.card,
                    onRegistrationButtonClick = onRegistrationButtonClick,
                    onCardClick = onCardClick,
                )

            is CardsUiState.MULTIPLE ->
                CardsMultipleContent(
                    paymentCards = PaymentCardsUiModel(cardsUiState.cards),
                    onCardClick = onCardClick,
                )
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
    onCardClick: (PaymentCardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PaymentCard(
            bankName = card.bankType.toBankName(),
            number = card.displayCardNumber(),
            expirationDate = card.displayExpirationDate(),
            cardholderName = card.upperCardholderName,
            backgroundColor = card.bankType.bgColor,
            modifier = Modifier.clickable { onCardClick(card) },
        )
        CardRegistrationButton(onClick = onRegistrationButtonClick)
    }
}

@Composable
private fun CardsMultipleContent(
    paymentCards: PaymentCardsUiModel,
    onCardClick: (PaymentCardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        paymentCards.cards.forEach { card ->
            PaymentCard(
                bankName = card.bankType.toBankName(),
                number = card.displayCardNumber(),
                expirationDate = card.displayExpirationDate(),
                cardholderName = card.upperCardholderName,
                backgroundColor = card.bankType.bgColor,
                modifier = Modifier.clickable { onCardClick(card) },
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) cards: CardsUiState,
) {
    AndroidpaymentsTheme {
        CardsScreen(
            onRegisterCardClick = {},
            onEditCardClick = { _, _ -> },
            viewModel = CardsScreenViewModel(cards),
        )
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
                    bankType = BankTypeUiModel.HYUNDAI,
                    number = CardNumberUiModel("1234567812345678"),
                    expirationDate = CardExpirationDateUiModel("0301"),
                    cardholderName = CardholderNameUiModel("DICE", 30),
                )
            }
    }
}
