package woowacourse.payments.ui.cardlist.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import woowacourse.payments.R
import woowacourse.payments.ui.cardlist.util.navigateToAddCard
import woowacourse.payments.ui.cardlist.util.navigateToEditCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardListScreen(
    modifier: Modifier = Modifier,
    initialCards: ImmutableList<CardUiModel> = persistentListOf(),
) {
    var cards by rememberSaveable(
        saver =
            Saver(
                save = { stateList ->
                    stateList.value.map { card -> with(CardUiModel.Saver) { save(card)!! } }
                },
                restore = { savedList ->
                    mutableStateOf(
                        savedList.map { item -> CardUiModel.Saver.restore(item)!! }.toImmutableList(),
                    )
                },
            ),
    ) {
        mutableStateOf(initialCards)
    }
    val onAddCard: (CardUiModel) -> Unit = { newCard ->
        cards = (cards + newCard).toImmutableList()
    }
    val onEditCard: (CardUiModel) -> Unit = { editedCard ->
        cards =
            cards
                .map { if (it.number == editedCard.number) editedCard else it }
                .toImmutableList()
    }

    val context = LocalContext.current
    val addCardLauncher = GenerateAddCardLauncher(onAddCard, context)
    val editCardLauncher =
        GenerateEditCardLauncher(context = context, editCard = onEditCard)

    AndroidpaymentsTheme {
        Scaffold(
            topBar = {
                CardListTopBar(
                    showAddCardBtn = cards.size < 10,
                    onAddCardClick = { navigateToAddCard(context, addCardLauncher) },
                )
            },
            modifier = modifier.fillMaxWidth(),
        ) { padding ->
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier =
                    Modifier
                        .padding(padding)
                        .fillMaxWidth(),
            ) {
                if (cards.isEmpty()) {
                    item {
                        Text(
                            modifier = Modifier.padding(top = 32.dp),
                            text = stringResource(R.string.card_list_empty_prompt),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }
                }
                items(cards.size) { index ->
                    PaymentCard(
                        modifier =
                            Modifier
                                .padding(top = 12.dp, bottom = 24.dp)
                                .clickable {
                                    navigateToEditCard(context, editCardLauncher, cards[index].toDomain())
                                },
                        content = { RegisterPaymentCard(cards[index]) },
                    )
                }
                if (cards.size <= 1) {
                    item {
                        PaymentCard(
                            modifier = Modifier.padding(top = 12.dp),
                            content = {
                                AddCardBtn(onClick = {
                                    navigateToAddCard(context, addCardLauncher)
                                })
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GenerateCardListPreview() {
    CardListScreen()
}
