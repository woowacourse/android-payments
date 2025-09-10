package woowacourse.payments.ui.cardlist.composable

import android.app.Activity
import android.os.Build
import android.util.Log
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardlist.util.navigateToAddCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun GenerateCardListView() {
    Log.d("test", "cardlist")
    val cards: MutableList<Card> = remember { mutableStateListOf() }
    val context = LocalContext.current
    val addCardLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            Log.d("test", "success")
            if (result.resultCode == Activity.RESULT_OK) {
                val card =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra("card", Card::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        result.data?.getParcelableExtra<Card>("card")
                    }
                if (card != null) {
                    cards.add(card)
                    Toast.makeText(context, "카드가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    AndroidpaymentsTheme {
        Scaffold(
            topBar = { CardListTopBar(context, addCardLauncher, cards.size >= 2) },
            modifier = Modifier.fillMaxWidth(),
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
                        text = "새로운 카드를 등록해주세요",
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
