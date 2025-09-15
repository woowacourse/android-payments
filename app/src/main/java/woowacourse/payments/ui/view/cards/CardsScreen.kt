package woowacourse.payments.ui.view.cards

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.PaymentToolbar
import woowacourse.payments.ui.component.RegisteredCard
import woowacourse.payments.ui.core.CardType
import woowacourse.payments.ui.core.CompanyResourceProvider
import woowacourse.payments.ui.core.Event
import woowacourse.payments.ui.core.getParcelableCompat
import woowacourse.payments.ui.preview.CardsPreviewParameterProvider
import woowacourse.payments.ui.preview.OneCardPreviewParameterProvider
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.view.cards.CardsActivity.Companion.EXTRA_CARD

@Composable
fun CardsScreen(onAddCardClick: (ManagedActivityResultLauncher<Intent, ActivityResult>) -> Unit) {
    val resourceProvider = CompanyResourceProvider()

    val cardUiStateHolder =
        rememberSaveable(saver = CardUiStateHolder.Saver) {
            CardUiStateHolder()
        }

    var uiEvent by remember {
        mutableStateOf<Event<CardScreenUiEvent>>(
            Event(CardScreenUiEvent.Idle),
        )
    }
    val activityResultLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getParcelableCompat<SerializationCard>(EXTRA_CARD)?.let { newCard ->
                    cardUiStateHolder.addCard(newCard)
                    uiEvent = Event(CardScreenUiEvent.CompleteAddCard)
                }
            }
        }

    Scaffold(
        topBar = {
            PaymentToolbar(
                onAddClick = {
                    onAddCardClick(activityResultLauncher)
                },
                addButtonVisible = cardUiStateHolder.toolbarActionButtonVisibility,
            )
        },
    ) { innerPadding ->
        CardsScreen(
            resourceProvider = resourceProvider,
            uiState = cardUiStateHolder.uiState,
            uiEvent = uiEvent,
            onClickCard = { cardType ->
                if (cardType is CardType.Empty) {
                    onAddCardClick(activityResultLauncher)
                }
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

private const val CARD_NUMBER_GROUP_SIZE = 4
private const val CARD_NUMBER_SEPARATOR = " - "
private const val CARD_EXPIRE_DATE_GROUP_SIZE = 2
private const val CARD_EXPIRE_DATE_SEPARATOR = " / "
private const val CARD_MASKING_CHAR = "*"

@Composable
fun CardsScreen(
    resourceProvider: CompanyResourceProvider,
    uiState: CardsUiState,
    uiEvent: Event<CardScreenUiEvent>,
    onClickCard: (CardType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.card_list_add_new_card)

    val event = uiEvent.getContentIfNotHandled()
    LaunchedEffect(event) {
        event?.let {
            when (it) {
                CardScreenUiEvent.CompleteAddCard -> {
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                }

                CardScreenUiEvent.Idle -> Unit
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            CardsUiState.EMPTY ->
                EmptyCardContent(resourceProvider, onClickCard)

            is CardsUiState.SINGLE ->
                SingleCardComponent(resourceProvider, uiState.state, onClickCard)

            is CardsUiState.MULTIPLE ->
                MultipleCardContent(resourceProvider, uiState.state, onClickCard)
        }
    }
}

@Composable
fun EmptyCardContent(
    resourceProvider: CompanyResourceProvider,
    onClickCard: (CardType) -> Unit,
) {
    Text(
        text = stringResource(R.string.card_list_empty),
        fontSize = 22.sp,
        modifier = Modifier.padding(top = 50.dp),
    )

    PaymentCard(
        resourceProvider = resourceProvider,
        cardType = CardType.Empty,
        onClick = onClickCard,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.content_description_card_list_empty),
            )
        },
        modifier =
            Modifier
                .padding(top = 18.dp),
    )
}

@Composable
fun SingleCardComponent(
    resourceProvider: CompanyResourceProvider,
    card: Card,
    onClickCard: (CardType) -> Unit,
) {
    PaymentCard(
        resourceProvider = resourceProvider,
        cardType = CardType.Registered(card.bank),
        content = {
            RegisteredCard(
                card,
                CARD_NUMBER_GROUP_SIZE,
                CARD_NUMBER_SEPARATOR,
                CARD_MASKING_CHAR,
                CARD_EXPIRE_DATE_GROUP_SIZE,
                CARD_EXPIRE_DATE_SEPARATOR,
            )
        },
        bank = card.bank,
        modifier =
            Modifier
                .padding(top = 30.dp)
                .shadow(8.dp),
    )
    PaymentCard(
        resourceProvider = resourceProvider,
        cardType = CardType.Empty,
        onClick = { onClickCard(CardType.Empty) },
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.content_description_card_list_empty),
            )
        },
        modifier =
            Modifier
                .padding(top = 30.dp),
    )
}

@Composable
fun MultipleCardContent(
    resourceProvider: CompanyResourceProvider,
    cards: List<Card>,
    onClickCard: (CardType) -> Unit,
) {
    cards.forEach { card ->
        val cardType = CardType.Registered(card.bank)

        PaymentCard(
            resourceProvider = resourceProvider,
            cardType = cardType,
            onClick = { onClickCard(cardType) },
            content = {
                RegisteredCard(
                    card,
                    CARD_NUMBER_GROUP_SIZE,
                    CARD_NUMBER_SEPARATOR,
                    CARD_MASKING_CHAR,
                    CARD_EXPIRE_DATE_GROUP_SIZE,
                    CARD_EXPIRE_DATE_SEPARATOR,
                )
            },
            bank = card.bank,
            modifier =
                Modifier
                    .padding(top = 30.dp)
                    .shadow(8.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardScreenPreview() {
    CardsScreen(
        CompanyResourceProvider(),
        CardsUiState.EMPTY,
        Event(CardScreenUiEvent.Idle),
        {},
    )
}

@Composable
@Preview(showBackground = true)
fun OneCardScreenPreview(
    @PreviewParameter(OneCardPreviewParameterProvider::class) card: Card,
) {
    CardsScreen(
        CompanyResourceProvider(),
        CardsUiState.SINGLE(card),
        Event(CardScreenUiEvent.Idle),
        {},
    )
}

@Composable
@Preview(showBackground = true)
fun CardsScreenPreview(
    @PreviewParameter(CardsPreviewParameterProvider::class) cards: List<Card>,
) {
    CardsScreen(
        CompanyResourceProvider(),
        CardsUiState.MULTIPLE(cards),
        Event(CardScreenUiEvent.Idle),
        {},
    )
}
