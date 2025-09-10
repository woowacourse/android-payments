package woowacourse.payments.ui.cards.components

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityOptionsCompat
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardHolderUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.ExpirationDateUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity

@Composable
fun Cards(
    scrollState: ScrollState,
    cardAddLauncher: ActivityResultLauncher<Intent>,
    cardList: List<PaymentCardUiModel>,
    modifier: Modifier = Modifier,
    minimumCardCountForAddButton: Int = 0,
) {
    Column(
        modifier =
            modifier
                .padding(top = 12.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        val context = LocalContext.current

        cardList.forEach { card ->
            CardImage(
                paymentCard = card,
            )
        }

        if (cardList.size <= minimumCardCountForAddButton) {
            if (cardList.isEmpty()) {
                Text(
                    text = stringResource(R.string.cards_no_card),
                )
            }
            AddCardImage {
                val intent = NewCardActivity.newIntent(context)
                cardAddLauncher.launch(intent)
            }
        }
    }
}

private fun getDummyCardAddLauncher(): ActivityResultLauncher<Intent> =
    object : ActivityResultLauncher<Intent>() {
        override val contract: ActivityResultContract<Intent, *>
            get() = TODO("Not yet implemented")

        override fun launch(
            input: Intent,
            options: ActivityOptionsCompat?,
        ) = Unit

        override fun unregister() = Unit
    }

@Preview(showBackground = true, name = "카드가 없을 때")
@Composable
private fun CardsPreview_NoCards() {
    Cards(
        scrollState = rememberScrollState(),
        cardAddLauncher = getDummyCardAddLauncher(),
        cardList = emptyList(),
        minimumCardCountForAddButton = 1,
    )
}

@Preview(showBackground = true, name = "카드가 하나 있을 때")
@Composable
private fun CardsPreview_OneCard_AddButtonVisible() {
    val sampleCard =
        PaymentCardUiModel(
            cardNumber = CardNumberUiModel("1234123412341234"),
            expirationDate = ExpirationDateUiModel("1225"),
            cardHolder = CardHolderUiModel("김환노"),
        )
    Cards(
        scrollState = rememberScrollState(),
        cardAddLauncher = getDummyCardAddLauncher(),
        cardList = listOf(sampleCard),
        minimumCardCountForAddButton = 1,
    )
}

@Preview(showBackground = true, name = "카드가 여러 개 있을 때 (추가 버튼 숨김)")
@Composable
private fun CardsPreview_MultipleCards_AddButtonHidden() {
    val sampleCards =
        listOf(
            PaymentCardUiModel(
                cardNumber = CardNumberUiModel("1234123412341234"),
                expirationDate = ExpirationDateUiModel("0611"),
                cardHolder = CardHolderUiModel("김환노"),
            ),
            PaymentCardUiModel(
                cardNumber = CardNumberUiModel("1234123412341234"),
                expirationDate = ExpirationDateUiModel("0511"),
                cardHolder = CardHolderUiModel("김공백"),
            ),
        )
    Cards(
        scrollState = rememberScrollState(),
        cardAddLauncher = getDummyCardAddLauncher(),
        cardList = sampleCards,
        minimumCardCountForAddButton = 1,
    )
}
