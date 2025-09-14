package woowacourse.payments.ui.cardlist.composable

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.cardlist.util.navigateToAddCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableExtraCompat

@Composable
fun GenerateCardListView(modifier: Modifier = Modifier) {
    val cards: MutableList<Card> = remember { mutableStateListOf() }
    val context = LocalContext.current
    val addCardLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val card = result.data?.getParcelableExtraCompat<Card>(AddCardActivity.EXTRA_CARD)
                if (card != null) {
                    cards.add(card)
                    Toast
                        .makeText(
                            context,
                            context.getString(R.string.card_list_card_registered_toast),
                            Toast.LENGTH_SHORT,
                        ).show()
                }
            }
        }
    AndroidpaymentsTheme {
        Scaffold(
            topBar = { CardListTopBar(showAddCardBtn = cards.size < 10, onAddCardClick = { navigateToAddCard(context, addCardLauncher) }) },
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
                        modifier = Modifier.padding(top = 12.dp, bottom = 24.dp),
                        content = { RegisterPaymentCard(card) },
                    )
                }
                if (cards.size <= 1) {
                    PaymentCard(
                        modifier = Modifier.padding(top = 12.dp),
                        color = Color.Gray,
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
