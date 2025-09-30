package woowacourse.payments.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.newCard.CardScreenUiState
import woowacourse.payments.ui.PaymentCard

@Composable
fun CardListScreen(
    uiState: CardScreenUiState,
    onAddClick: () -> Unit,
    onClick: (CardUiModel) -> Unit,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                actions = {
                    if (uiState is CardScreenUiState.MultipleCard) {
                        TextButton(
                            onClick = onAddClick,
                            content = {
                                Text(
                                    text = stringResource(R.string.add_new_card_button),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                )
                            },
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            when (uiState) {
                is CardScreenUiState.Empty -> {
                    item {
                        AddNewCardText()
                    }
                    item {
                        AddNewCard(onAddClick = onAddClick)
                    }
                }

                is CardScreenUiState.SingleCard -> {
                    item {
                        PaymentCard(
                            card = uiState.card,
                            modifier = Modifier.clickable{ onClick(uiState.card) },
                        )
                    }
                    item {
                        AddNewCard(onAddClick = onAddClick)
                    }
                }

                is CardScreenUiState.MultipleCard -> {
                    items(uiState.cards) { card ->
                        PaymentCard(
                            card = card,
                            modifier = Modifier.clickable{ onClick(card) },
                        )
                    }
                }
            }
        }
    }
}
