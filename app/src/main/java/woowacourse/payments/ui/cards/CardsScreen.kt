package woowacourse.payments.ui.cards

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.cards.components.AddCardImage
import woowacourse.payments.ui.cards.components.CardItem
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.NewCardActivity.Companion.EXTRA_NEW_CARD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen() {
    val context = LocalContext.current

    val cardList =
        rememberSaveable { mutableStateListOf<PaymentCardUiModel>() }

    val cardAddLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard = activityResult.data?.getParcelableExtra<PaymentCardUiModel>(EXTRA_NEW_CARD)
                newCard?.let { cardList.add(it) }
            }
        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Payments",
                    )
                },
                actions = {
                    if (cardList.size > 1) {
                        Text(
                            modifier =
                                Modifier
                                    .padding(end = 20.dp),
                            text = "추가",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(top = 12.dp)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            cardList.forEach { card ->
                CardItem(
                    paymentCard = card,
                )
            }

            if (cardList.size <= 1) {
                if (cardList.isEmpty()) {
                    Text(
                        text = "새로운 카드를 등록해주세요",
                    )
                }
                AddCardImage {
                    val intent = NewCardActivity.newIntent(context)
                    cardAddLauncher.launch(intent)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardScreenPreview() {
    CardsScreen()
}
