package woowacourse.payments.ui.view.cards

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import woowacourse.payments.ui.core.Event
import woowacourse.payments.ui.core.ext.getParcelableCompat
import woowacourse.payments.ui.preview.CardsPreviewParameterProvider
import woowacourse.payments.ui.preview.OneCardPreviewParameterProvider
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.state.CardState
import woowacourse.payments.ui.view.cards.CardsActivity.Companion.EXTRA_CARD_ADD
import woowacourse.payments.ui.view.cards.CardsActivity.Companion.EXTRA_CARD_MODIFY
import woowacourse.payments.ui.view.cards.CardsActivity.Companion.EXTRA_CARD_MODIFY_INDEX

@Composable
fun CardsScreen(
    onClickAddCard: (ManagedActivityResultLauncher<Intent, ActivityResult>) -> Unit,
    onClickModifyCard: (ManagedActivityResultLauncher<Intent, ActivityResult>, CardState, Int) -> Unit,
) {
    val cardUiStateHolder =
        rememberSaveable(saver = CardUiStateHolder.Saver) {
            CardUiStateHolder()
        }

    var uiEvent by remember {
        mutableStateOf<Event<CardScreenUiEvent>>(
            Event(CardScreenUiEvent.Idle),
        )
    }

    val context = LocalContext.current
    val toastMessage = stringResource(R.string.card_list_add_new_card)

    val event = uiEvent.getContentIfNotHandled()
    LaunchedEffect(Unit) {
        event?.let {
            when (it) {
                CardScreenUiEvent.CompleteAddCard -> {
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                }

                CardScreenUiEvent.Idle -> Unit
            }
        }
    }

    val activityResultLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data
                    ?.getParcelableCompat<SerializationCard>(EXTRA_CARD_ADD)
                    ?.let { newCard ->
                        cardUiStateHolder.addCard(newCard)
                        uiEvent = Event(CardScreenUiEvent.CompleteAddCard)
                    }

                result.data
                    ?.getParcelableCompat<SerializationCard>(EXTRA_CARD_MODIFY)
                    ?.let { newCard ->
                        result.data
                            ?.getIntExtra(EXTRA_CARD_MODIFY_INDEX, -1)
                            ?.let { index ->
                                cardUiStateHolder.modifyCardAt(index, newCard)
                            }
                    }
            }
        }

    Scaffold(
        topBar = {
            PaymentToolbar(
                onAddClick = {
                    onClickAddCard(activityResultLauncher)
                },
                addButtonVisible = cardUiStateHolder.toolbarActionButtonVisibility,
            )
        },
    ) { innerPadding ->
        CardsScreen(
            uiState = cardUiStateHolder.uiState,
            onClickAddCard = { cardType ->
                onClickAddCard(activityResultLauncher)
            },
            onClickModifyCard = { cardType, index ->
                onClickModifyCard(activityResultLauncher, cardType, index)
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
    uiState: CardsUiState,
    onClickAddCard: (CardState) -> Unit,
    onClickModifyCard: (CardState, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        CardsUiState.EMPTY ->
            EmptyCardComponent(
                onClickCard = { onClickAddCard(CardState.Empty) },
                modifier = modifier,
            )

        is CardsUiState.SINGLE ->
            SingleCardComponent(
                registeredCard = uiState.card,
                onClickAddCard = onClickAddCard,
                onClickModifyCard = onClickModifyCard,
                modifier = modifier,
            )

        is CardsUiState.MULTIPLE ->
            MultipleCardComponent(
                uiState.cards,
                onClickModifyCard,
                modifier = modifier,
            )
    }
}

@Composable
fun EmptyCardComponent(
    onClickCard: (CardState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Text(
            text = stringResource(R.string.card_list_empty),
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 10.dp),
        )

        PaymentCard(
            cardState = CardState.Empty,
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.content_description_card_list_empty),
                )
            },
            modifier =
                Modifier
                    .clickable(onClick = { onClickCard(CardState.Empty) }),
        )
    }
}

@Composable
fun SingleCardComponent(
    registeredCard: CardState.Registered,
    onClickAddCard: (CardState) -> Unit,
    onClickModifyCard: (CardState, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        PaymentCard(
            cardState = registeredCard,
            content = {
                RegisteredCard(
                    registeredCard.card,
                    CARD_NUMBER_GROUP_SIZE,
                    CARD_NUMBER_SEPARATOR,
                    CARD_MASKING_CHAR,
                    CARD_EXPIRE_DATE_GROUP_SIZE,
                    CARD_EXPIRE_DATE_SEPARATOR,
                )
            },
            modifier =
                Modifier
                    .shadow(8.dp)
                    .clickable(onClick = { onClickModifyCard(registeredCard, 0) }),
        )
        PaymentCard(
            cardState = CardState.Empty,
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.content_description_card_list_empty),
                )
            },
            modifier =
                Modifier
                    .clickable(onClick = { onClickAddCard(CardState.Empty) }),
        )
    }
}

@Composable
fun MultipleCardComponent(
    registeredCards: List<CardState.Registered>,
    onClickModifyCard: (CardState, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = 30.dp, bottom = 30.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        itemsIndexed(registeredCards) { index, card ->
            PaymentCard(
                cardState = card,
                content = {
                    RegisteredCard(
                        card.card,
                        CARD_NUMBER_GROUP_SIZE,
                        CARD_NUMBER_SEPARATOR,
                        CARD_MASKING_CHAR,
                        CARD_EXPIRE_DATE_GROUP_SIZE,
                        CARD_EXPIRE_DATE_SEPARATOR,
                    )
                },
                modifier =
                    Modifier
                        .shadow(8.dp)
                        .clickable(onClick = { onClickModifyCard(card, index) }),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardScreenPreview() {
    CardsScreen(
        CardsUiState.EMPTY,
        {},
        { _, _ -> },
    )
}

@Composable
@Preview(showBackground = true)
fun SingleCardScreenPreview(
    @PreviewParameter(OneCardPreviewParameterProvider::class) card: Card,
) {
    CardsScreen(
        CardsUiState.SINGLE(card),
        {},
        { _, _ -> },
    )
}

@Composable
@Preview(showBackground = true)
fun CardsScreenPreview(
    @PreviewParameter(CardsPreviewParameterProvider::class) cards: List<Card>,
) {
    CardsScreen(
        CardsUiState.MULTIPLE(cards),
        {},
        { _, _ -> },
    )
}
