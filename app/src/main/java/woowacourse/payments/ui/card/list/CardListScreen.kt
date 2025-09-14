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
fun CardListScreen(
    cards: List<CardUiModel> = emptyList(),
    onAddNewCardClick: (CardUiModel) -> Unit,
) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val newCard =
                    result.data?.getParcelableExtra<CardUiModel>(
                        CardRegisterActivity.EXTRA_NEW_CARD,
                    )
                newCard?.let { onAddNewCardClick(it) }
            }
        }

    fun launchCardRegister() {
        launcher.launch(CardRegisterActivity.newIntent(context))
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Payments") },
                actions = {
                    if (cards.size > 1) {
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
            when (cards.size) {
                0 -> {
                    NoCardScreen(onAddNewCardClick = { launchCardRegister() })
                }

                1 -> {
                    OneCardScreen(
                        card = cards.first(),
                        onAddNewCardClick = { launchCardRegister() },
                    )
                }

                else -> {
                    MultipleCardsScreen(cards = cards)
                }
            }
        }
    }
}
