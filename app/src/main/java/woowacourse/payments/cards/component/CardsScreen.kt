package woowacourse.payments.cards.component

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.Card
import woowacourse.payments.EXTRA_CARD
import woowacourse.payments.cardaddition.CardAdditionActivity
import woowacourse.payments.getParcelableCompat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    cards: List<Card>,
    addCard: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val cardAddLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val card: Card =
                    result.data?.getParcelableCompat(EXTRA_CARD)
                        ?: return@rememberLauncherForActivityResult
                addCard(card)
            }
        }
    val navigateToCardAdditionActivity: () -> Unit =
        { cardAddLauncher.launch(Intent(context, CardAdditionActivity::class.java)) }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopAppBar(
                addCardAction = if (cards.size > 1) navigateToCardAdditionActivity else null,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
        ) {
            when (cards.size) {
                0 ->
                    NoCardContent(
                        addCard = navigateToCardAdditionActivity,
                        modifier = Modifier.fillMaxSize(),
                    )

                1 ->
                    OneCardContent(
                        card = cards.first(),
                        addCard = navigateToCardAdditionActivity,
                        modifier = Modifier.fillMaxSize(),
                    )

                else ->
                    MultipleCardContent(
                        cards = cards,
                        modifier = Modifier.fillMaxSize(),
                    )
            }
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) cards: List<Card>,
) {
    AndroidpaymentsTheme {
        CardsScreen(
            cards = cards,
            addCard = {},
        )
    }
}

private class CardsScreenPreviewParameterProvider : PreviewParameterProvider<List<Card>> {
    override val values: Sequence<List<Card>> =
        sequenceOf(
            emptyList(),
            listOf(
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                ),
            ),
            listOf(
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                ),
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                ),
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                ),
            ),
        )
}
