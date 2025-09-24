package woowacourse.payments.ui.card.list

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.card.register.CardRegisterActivity
import woowacourse.payments.ui.model.CardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen() {
    val context = LocalContext.current
    val stateHolder = remember { CardListStateHolder() }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val card =
                    result.data?.getParcelableExtra<CardUiModel>(
                        CardRegisterActivity.EXTRA_NEW_CARD,
                    )
                card?.let {
                    if (stateHolder.contains(it.id)) {
                        stateHolder.updateCard(it)
                    } else {
                        stateHolder.addNewCard(it)
                    }
                }
            }
        }

    fun launchCardRegister() {
        launcher.launch(CardRegisterActivity.newIntent(context))
    }

    fun launchCardEdit(card: CardUiModel) {
        launcher.launch(CardRegisterActivity.editCardIntent(card))
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Payments") },
                actions = {
                    if (stateHolder.uiState is CardListUiState.Multiple) {
                        TextButton(
                            onClick = { launchCardRegister() },
                            modifier = Modifier.padding(end = 20.dp),
                        ) {
                            Text(
                                text = "추가",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (stateHolder.uiState) {
                CardListUiState.Empty -> {
                    NoCardScreen(onAddNewCardClick = { launchCardRegister() })
                }

                is CardListUiState.Single -> {
                    OneCardScreen(
                        card = (stateHolder.uiState as CardListUiState.Single).card,
                        onAddNewCardClick = { launchCardRegister() },
                        onEditCardClick = { selectedCard -> launchCardEdit(selectedCard) },
                    )
                }

                is CardListUiState.Multiple -> {
                    MultipleCardsScreen(
                        cards = (stateHolder.uiState as CardListUiState.Multiple).cards,
                        onEditCardClick = { selectedCard -> launchCardEdit(selectedCard) },
                    )
                }
            }
        }
    }
}
