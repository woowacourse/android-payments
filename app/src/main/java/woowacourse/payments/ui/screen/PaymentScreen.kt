package woowacourse.payments.ui.screen

import android.app.Activity
import android.content.Intent
import android.widget.Toast
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
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.component.payments.PaymentsColumn
import woowacourse.payments.ui.component.payments.PaymentsTopBar


@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    cards: List<PaymentCard>,
    onAddNewCardClick: () -> Unit
) {

    val cardList = remember { mutableStateListOf<PaymentCard>() }
    val screenType = remember { mutableStateListOf<PaymentCardCount>() }
    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                // 카드 추가 성공!


            }
        }

    Scaffold(
        modifier = modifier,
        topBar = {
            PaymentsTopBar(onAddNewCardClick = {

                onAddNewCardClick()

            })
        }
    ) { paddingValues: PaddingValues ->
        PaymentsColumn(
            onClickAddCard = onAddNewCardClick,
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