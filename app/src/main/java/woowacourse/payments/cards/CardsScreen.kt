package woowacourse.payments.cards

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.NewCardActivity
import woowacourse.payments.R
import woowacourse.payments.cards.component.CardsTopBar
import woowacourse.payments.cards.component.EmptyCard
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.newcard.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.PaymentCard
import woowacourse.payments.util.parcelable

@Composable
fun CardsScreen(
    cardsStateHolder: CardsStateHolder = remember { CardsStateHolder() },
    onCardAdded: () -> Unit = {},
) {
    val context = LocalContext.current

    // 카드 추가 Activity result를 처리하기 위한 launcher
    val cardAddLauncher: ActivityResultLauncher<Intent> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val cardParcelable: CardParcelable? =
                    activityResult.data?.parcelable<CardParcelable>(NewCardActivity.KEY_CARD)

                val card: Card? = cardParcelable?.toDomainOrNull()

                if (card != null) {
                    cardsStateHolder.add(card)
                }
                onCardAdded()
            }
        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CardsTopBar(
                onAddClick = { launchNewCardActivity(context, cardAddLauncher) },
                modifier = Modifier.padding(),
                isAddable = cardsStateHolder.cards.size > 1,
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 12.dp),
        ) {
            if (cardsStateHolder.isCardsEmpty()) {
                Text(
                    stringResource(R.string.text_no_card),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 20.dp, bottom = 32.dp),
                )
            }

            cardsStateHolder.cards.forEach { card: Card ->
                PaymentCard(
                    card = card,
                    cardCompanyUiModel = CardCompanyUiModel.from(card.cardCompany),
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            if (cardsStateHolder.shouldDisplayEmptyCard()) {
                EmptyCard(onClick = { launchNewCardActivity(context, cardAddLauncher) })
            }
        }
    }
}

private fun launchNewCardActivity(
    context: Context,
    launcher: ActivityResultLauncher<Intent>,
) {
    val intent = NewCardActivity.newIntent(context)
    launcher.launch(intent)
}

@Preview
@Composable
private fun CardsScreenPreview() {
    AndroidpaymentsTheme {
        CardsScreen(
            cardsStateHolder =
                CardsStateHolder().apply {
                    add(
                        Card(
                            cardNumber = CardNumber("1234567812345678"),
                            expiredDate = ExpiredDate.of(4, 26)!!,
                            ownerName = OwnerName("크림"),
                            password = Password("1234"),
                            cardCompany = CardCompany.KAKAO,
                        ),
                    )
                },
        )
    }
}
