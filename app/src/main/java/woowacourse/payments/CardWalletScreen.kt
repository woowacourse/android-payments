package woowacourse.payments

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.component.CardWalletTopBar
import woowacourse.payments.component.EmptyCard
import woowacourse.payments.component.RegisteredCard
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun CardWalletScreen(
    cardList: List<CardUiModel>,
    onCardAddResult: (CardUiModel) -> Unit
) {
    val context = LocalContext.current

    val cardAddLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getParcelableExtra<CardUiModel>(NewCardScreenActivity.ADD_NEW_CARD)
                ?.let {
                    onCardAddResult(it)
                    Toast.makeText(context, "카드가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
        }
    }

    Scaffold(
        topBar = {
            CardWalletTopBar(
                isAddable = cardList.size > 1,
                onAddCardClick = {
                    val intent = NewCardScreenActivity.newIntent(context)
                    cardAddLauncher.launch(intent)
                }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            when {
                cardList.isEmpty() -> {
                    Spacer(modifier = Modifier
                        .height(32.dp)
                        .fillMaxSize())
                    Text("새로운 카드를 등록해주세요", fontSize = 18.sp, fontWeight = FontWeight.W700)
                    Spacer(modifier = Modifier
                        .height(32.dp)
                        .fillMaxSize())
                    EmptyCard(
                        onClick = {
                            val intent = NewCardScreenActivity.newIntent(context)
                            cardAddLauncher.launch(intent)
                        }
                    )
                }

                cardList.size == 1 -> {
                    RegisteredCard(cardList.first())
                    Spacer(modifier = Modifier.height(36.dp))
                    EmptyCard(
                        onClick = {
                            val intent = NewCardScreenActivity.newIntent(context)
                            cardAddLauncher.launch(intent)
                        }
                    )
                }

                else -> {
                    repeat(cardList.size) {
                        RegisteredCard(cardList[it])
                        Spacer(modifier = Modifier.height(36.dp))
                    }
                }
            }
        }
    }
}
