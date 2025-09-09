package woowacourse.payments.ui.screen

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.CardAdditionActivity
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.component.payments.PaymentsColumn
import woowacourse.payments.ui.component.payments.PaymentsTopBar
import kotlin.jvm.java


@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    cards: List<PaymentCard>,
    onAddNewCardClick: () -> Unit
) {
    val context = LocalContext.current
    val cards = remember { mutableStateListOf<PaymentCard>() }
    val screenType = remember { mutableStateListOf<PaymentCardCount>() }
    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                // 카드 추가 성공!
                val newCard = activityResult.data?.getParcelableExtra<PaymentCard>("newCard")
                if (newCard != null) {
                    cards.add(newCard)
                    Log.d("test", "PaymentScreen: $cards")
                }
            }
        }

    fun openAddCardWithResult() {
        val intent = Intent(context, CardAdditionActivity::class.java)
        cardAddLauncher.launch(intent)
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            PaymentsTopBar(onAddNewCardClick = {
                onAddNewCardClick()
                openAddCardWithResult()
            })
        }
    ) { paddingValues: PaddingValues ->
        PaymentsColumn(
            onClickAddCard = {
                onAddNewCardClick()
                openAddCardWithResult()
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}



@Preview
@Composable
private fun PaymentScreenPreview() {
    PaymentScreen(cards = emptyList(), onAddNewCardClick = {})
}

sealed class PaymentCardCount {
    data object Empty : PaymentCardCount()
    data object One : PaymentCardCount()
    data object MoreThanOne : PaymentCardCount()
}