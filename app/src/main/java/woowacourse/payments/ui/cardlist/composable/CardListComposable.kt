package woowacourse.payments.ui.cardlist.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardlist.util.navigateToAddCard
import woowacourse.payments.ui.cardlist.util.navigateToEditCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun GenerateCardListView(modifier: Modifier = Modifier) {
    var cards by remember { mutableStateOf(emptyList<Card>()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val addCardLauncher = GenerateAddCardLauncher({ card -> cards = cards + card }, context)
    val editCardLauncher =
        GenerateEditCardLauncher(context = context, editCard = { card ->
            cards =
                cards.mapIndexed { index, oldCard ->
                    if (index == currentIndex) {
                        card
                    } else {
                        oldCard
                    }
                }
        })
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier =
                    Modifier
                        .padding(padding)
                        .fillMaxWidth(),
            ) {
                if (cards.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = 32.dp),
                        text = stringResource(R.string.card_list_empty_prompt),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
                for (card in cards) {
                    PaymentCard(
                        modifier =
                            Modifier
                                .padding(top = 12.dp, bottom = 24.dp)
                                .clickable(
                                    onClick = {
                                        currentIndex = cards.indexOf(card)
                                        navigateToEditCard(context, editCardLauncher, card)
                                    },
                                ),
                        content = { RegisterPaymentCard(card.toUiModel()) },
                    )
                }
                if (cards.size <= 1) {
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

@Preview(showBackground = true)
@Composable
private fun GenerateCardListPreview() {
    GenerateCardListView()
}
