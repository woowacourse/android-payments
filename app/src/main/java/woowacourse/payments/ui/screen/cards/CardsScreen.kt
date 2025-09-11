package woowacourse.payments.ui.screen.cards

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.component.CardsTopAppBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.screen.cards.CardsActivity.Companion.EXTRA_CARDS_REGISTER_NEW_CARD
import woowacourse.payments.ui.screen.registration.CardRegistrationActivity
import woowacourse.payments.ui.theme.TextGray

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    cardsScreenUiState: CardsScreenUiState = CardsScreenUiState(),
) {
    var uiState: CardsScreenUiState by remember { mutableStateOf(cardsScreenUiState) }
    val context = LocalContext.current

    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard: CardUiModel? =
                    activityResult.data?.getParcelableExtraCompat(
                        EXTRA_CARDS_REGISTER_NEW_CARD,
                    )
                newCard?.let {
                    uiState = uiState.updateUiStateWithCard(newCard)
                    Toast
                        .makeText(
                            context,
                            context.getString(R.string.cards_screen_registration_toast),
                            Toast.LENGTH_SHORT,
                        ).show()
                }
            }
        }

    Scaffold(
        topBar = {
            CardsTopAppBar(
                onRegistrationClick = {
                    val intent = CardRegistrationActivity.newIntent(context)
                    cardAddLauncher.launch(intent)
                },
                isVisibleRegistrationButton = uiState.isVisibleRegistrationButtonInTopBar(),
            )
        },
    ) { innerPadding ->
        CardsScreenContent(
            uiState = uiState,
            onClickRegistration = {
                val intent = CardRegistrationActivity.newIntent(context)
                cardAddLauncher.launch(intent)
            },
            modifier = modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun CardsScreenContent(
    uiState: CardsScreenUiState,
    onClickRegistration: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.hasNoContent()) {
            RegistrationGuideText()
            Spacer(modifier = Modifier.height(16.dp))
        }

        uiState.value.forEach { card: CardUiModel ->
            PaymentCard(card = card)
            Spacer(modifier = Modifier.height(36.dp))
        }

        if (uiState.isVisibleRegistrationBoxInContent()) {
            RegistrationBox(onClickRegistration)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun RegistrationGuideText() {
    Text(
        text = stringResource(R.string.cards_screen_registration_guide),
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun RegistrationBox(onClickRegistration: () -> Unit) {
    Box(
        modifier =
            Modifier
                .size(width = 208.dp, height = 124.dp)
                .background(Color.LightGray)
                .clickable { onClickRegistration() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.cards_screen_registration_symbol),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = TextGray,
        )
    }
}

@Preview(showBackground = true, name = "데이터 존재 X")
@Composable
private fun NoContentPreview() {
    val uiState: CardsScreenUiState = CardsScreenUiState(emptyList())
    CardsScreen(
        cardsScreenUiState = uiState,
    )
}

@Preview(showBackground = true, name = "데이터 1개 존재")
@Composable
private fun HasOneContentPreview() {
    val uiState: CardsScreenUiState =
        CardsScreenUiState(
            listOf(
                CardUiModel(
                    cardholderNameUiModel = CardholderNameUiModel("CREW"),
                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                    cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
                ),
            ),
        )

    CardsScreen(
        cardsScreenUiState = uiState,
    )
}

@Preview(showBackground = true, name = "데이터 2개 이상 존재")
@Composable
private fun HasMultipleContentPreview() {
    val uiState: CardsScreenUiState =
        CardsScreenUiState(
            listOf(
                CardUiModel(
                    cardholderNameUiModel = CardholderNameUiModel("CREW"),
                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                    cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
                ),
                CardUiModel(
                    cardholderNameUiModel = CardholderNameUiModel("CREW ABCDEFGHIJK"),
                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                    cardExpirationDateUiModel = CardExpirationDateUiModel("1188"),
                ),
            ),
        )
    CardsScreen(
        cardsScreenUiState = uiState,
    )
}
