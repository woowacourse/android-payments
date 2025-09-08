package woowacourse.payments.ui.screen.cardList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.component.CardListTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toPresentation

@Composable
fun CardListScreen(
    cards: List<CardUiModel>,
    navigateToAddCard: () -> Unit,
) {
    val showAddButtonInTopBar by remember { derivedStateOf { cards.size > 1 } }

    Scaffold(
        topBar = {
            CardListTopBar(
                showAddButton = showAddButtonInTopBar,
                onAddClick = navigateToAddCard,
            )
        },
    ) { innerPadding ->
        if (cards.isEmpty()) {
            EmptyCardList(
                modifier = Modifier.padding(innerPadding),
                onAddCardClick = navigateToAddCard,
            )
        } else {
            CardListContent(
                cards = cards,
                modifier = Modifier.padding(innerPadding),
                onAddCardClick = navigateToAddCard,
            )
        }
    }
}

@Composable
private fun EmptyCardList(
    modifier: Modifier = Modifier,
    onAddCardClick: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "새로운 카드를 등록해주세요",
            modifier =
                Modifier.semantics {
                    this.contentDescription = "새로운 카드 등록 텍스트"
                },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(32.dp))
        AddCardBox(onClick = onAddCardClick)
    }
}

@Composable
private fun CardListContent(
    cards: List<CardUiModel>,
    modifier: Modifier = Modifier,
    onAddCardClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (card in cards) {
            Spacer(modifier = Modifier.height(32.dp))
            PaymentCard(card = card)
        }

        if (cards.size == 1) {
            Spacer(modifier = Modifier.height(32.dp))
            AddCardBox(onClick = onAddCardClick)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun AddCardBox(onClick: () -> Unit) {
    Box(
        modifier =
            Modifier
                .size(width = 240.dp, height = 140.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.LightGray)
                .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(Icons.Default.Add, contentDescription = "카드 추가")
    }
}

@Preview(showBackground = true, name = "카드 0개")
@Composable
fun CardListScreenPreview_Empty() {
    AndroidpaymentsTheme {
        CardListScreen(
            cards = emptyList(),
            navigateToAddCard = { },
        )
    }
}

@Preview(showBackground = true, name = "카드 1개")
@Composable
fun CardListScreenPreview_OneCard() {
    AndroidpaymentsTheme {
        CardListScreen(
            cards =
                listOf(
                    Card(
                        number = CardNumber("1234567887654321"),
                        expired = Expired("1221"),
                        owner = CardOwner("aaaa"),
                        password = Password("1234"),
                    ).toPresentation(),
                ),
            navigateToAddCard = { },
        )
    }
}

@Preview(showBackground = true, name = "카드 2개")
@Composable
fun CardListScreenPreview_TwoCards() {
    AndroidpaymentsTheme {
        CardListScreen(
            cards =
                listOf(
                    Card(
                        number = CardNumber("1234567887654321"),
                        expired = Expired("1221"),
                        owner = CardOwner("aaaa"),
                        password = Password("1234"),
                    ).toPresentation(),
                    Card(
                        number = CardNumber("8765432112345678"),
                        expired = Expired("1122"),
                        owner = CardOwner("bbbb"),
                        password = Password("5678"),
                    ).toPresentation(),
                ),
            navigateToAddCard = { },
        )
    }
}
