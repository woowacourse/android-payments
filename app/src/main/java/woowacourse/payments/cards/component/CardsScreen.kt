package woowacourse.payments.cards.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.Card
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
    val cardAddLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val card: Card =
                    result.data?.getParcelableCompat("card")
                        ?: return@rememberLauncherForActivityResult
                addCard(card)
            }
        }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopAppBar(
                isAddActionVisible = cards.size > 1,
                addCard = { cardAddLauncher.launch(context) },
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            when (cards.size) {
                0 ->
                    NoCardContent(
                        addCard = { cardAddLauncher.launch(context) },
                        modifier = Modifier.fillMaxSize(),
                    )

                1 ->
                    OneCardContent(
                        card = cards.first(),
                        addCard = { cardAddLauncher.launch(context) },
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

private fun ManagedActivityResultLauncher<Intent, ActivityResult>.launch(context: Context) {
    launch(
        Intent(
            context,
            CardAdditionActivity::class.java,
        ),
    )
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
