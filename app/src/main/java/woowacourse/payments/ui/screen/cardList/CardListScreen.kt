package woowacourse.payments.ui.screen.cardList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.component.CardListTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.toBankUiModel

@Composable
fun CardListScreen(
    navigateToAddCard: () -> Unit,
    stateHolder: CardListStateHolder = remember { CardListStateHolder() },
) {
    val uiState = stateHolder.uiState

    Scaffold(
        topBar = {
            CardListTopBar(
                showAddButton = uiState.showAddButton,
                onAddClick = navigateToAddCard,
            )
        },
    ) { innerPadding ->
        if (uiState.cards.isEmpty()) {
            EmptyCardListContent(
                modifier = Modifier.padding(innerPadding),
                onClick = navigateToAddCard,
            )
        } else {
            CardListContent(
                cards = uiState.cards,
                enableScroll = uiState.cards.size > 1,
                onClick = navigateToAddCard,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
private fun CardListContent(
    cards: List<CardUiModel>,
    enableScroll: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .then(if (enableScroll) Modifier.verticalScroll(scrollState) else Modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        cards.forEach { card ->
            Spacer(modifier = Modifier.height(20.dp))
            PaymentCard(card = card)
        }

        if (cards.size <= 1) {
            Spacer(modifier = Modifier.height(32.dp))
            AddCardBox(onClick = onClick)
        }
    }
}

@Composable
private fun EmptyCardListContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.card_list_add_new_card),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        AddCardBox(onClick = onClick)
    }
}

@Composable
private fun AddCardBox(onClick: () -> Unit) {
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

class CardListPreviewProvider : PreviewParameterProvider<List<CardUiModel>> {
    override val values: Sequence<List<CardUiModel>> =
        sequenceOf(
            emptyList(),
            listOf(
                CardUiModel(
                    bankUiModel = BankType.KAKAOBANK.toBankUiModel(),
                    number = "1234567887654321",
                    expired = "1221",
                    owner = "HamBeomJoon",
                ),
            ),
            listOf(
                CardUiModel(
                    bankUiModel = BankType.LOTTE.toBankUiModel(),
                    number = "1234567887654321",
                    expired = "1221",
                    owner = "moondev03",
                ),
                CardUiModel(
                    bankUiModel = BankType.HYUNDAI.toBankUiModel(),
                    number = "8734578233123212",
                    expired = "0729",
                    owner = "meeple",
                ),
            ),
        )
}

@Preview(showBackground = true)
@Composable
fun CardListScreenPreview(
    @PreviewParameter(CardListPreviewProvider::class) cards: List<CardUiModel>,
) {
    AndroidpaymentsTheme {
        CardListScreen(
            navigateToAddCard = { },
            stateHolder = CardListStateHolder(CardListUiState(cards = cards)),
        )
    }
}
