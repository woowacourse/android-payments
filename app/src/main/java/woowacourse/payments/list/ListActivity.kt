package woowacourse.payments.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.edit.EditActivity
import woowacourse.payments.newCard.CardScreenUiState
import woowacourse.payments.newCard.NewCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.parcelable

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                var cardState by remember { mutableStateOf(CardScreenUiState.from(emptyList())) }

                val cardAddLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                        if (activityResult.resultCode == RESULT_OK) {
                            val newCard = activityResult.data?.parcelable<CardUiModel>("card")
                            newCard?.let { cardUiModel ->
                                cardState = CardScreenUiState.from(cardState.cards + cardUiModel)
                                Toast.makeText(this, "카드가 추가되었습니다.", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                val cardEditLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                        if (activityResult.resultCode == RESULT_OK) {
                            val editedCard = activityResult.data?.parcelable<CardUiModel>("card")
                            editedCard?.let { updated ->
                                cardState = CardScreenUiState.from(
                                    cardState.cards.map { if (it == updated) updated else it }
                                )
                            }
                        }
                    }

                CardListScreen(
                    uiState = cardState,
                    onAddClick = {
                        val intent = Intent(this, NewCardActivity::class.java)
                        cardAddLauncher.launch(intent)
                    },
                    onClick = { card ->
                        val intent = Intent(this, EditActivity::class.java).apply {
                            putExtra("card", card)
                        }
                        cardEditLauncher.launch(intent)
                    },
                )
            }
        }
    }
}

@Preview(name = "카드가 없을 때")
@Composable
private fun EmptyCardListPreview() {
    AndroidpaymentsTheme {
        CardListScreen(
            CardScreenUiState.from(emptyList()),
            onAddClick = {},
            onClick = {},
        )
    }
}

@Preview(name = "카드가 한 개일 때")
@Composable
private fun AddOneCardListPreview() {
    AndroidpaymentsTheme {
        CardListScreen(
            CardScreenUiState.from(
                listOf(
                    CardUiModel(
                        "0000000000000000",
                        "0925",
                        "1234",
                        "PARK JIWON",
                    ),
                ),
            ),
            onAddClick = {},
            onClick = {},
        )
    }
}

@Preview(name = "카드가 여러 개일 때")
@Composable
private fun AddTwoOrMoreCardListPreview() {
    AndroidpaymentsTheme {
        CardListScreen(
            CardScreenUiState.from(
                listOf(
                    CardUiModel(
                        "0000000000000000",
                        "1225",
                        "1234",
                        "PARK JIWON",
                    ),
                    CardUiModel(
                        "1234123412341234",
                        "0999",
                        "9999",
                        "TOMATO BASIL ADE",
                    ),
                ),
            ),
            onAddClick = {},
            onClick = {},
        )
    }
}
