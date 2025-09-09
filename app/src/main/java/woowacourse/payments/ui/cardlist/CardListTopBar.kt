package woowacourse.payments.ui.cardlist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    cards: SnapshotStateList<Card>,
    onAddCard: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.card_list_title),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        actions = {
            if (cards.size > 1) {
                Text(
                    text = stringResource(R.string.card_list_add_card_button_text),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onAddCard() },
                )
            }
        },
        modifier = Modifier.padding(horizontal = 16.dp),
    )
}

@SuppressLint("UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun CardListTopBarWithNoCardPreview() {
    AndroidpaymentsTheme {
        CardListTopBar(mutableStateListOf())
    }
}

@SuppressLint("UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun CardListTopBarWithTwoCardsPreview() {
    AndroidpaymentsTheme {
        CardListTopBar(
            mutableStateListOf(
                Card(
                    CardNumber("1234123412341234"),
                    ExpirationDate(YearMonth.of(2034, 12)),
                    CardholderName("디랙"),
                    Passcode("1234"),
                ),
                Card(
                    CardNumber("1234123412341234"),
                    ExpirationDate(YearMonth.of(2034, 12)),
                    CardholderName("디랙"),
                    Passcode("1234"),
                ),
            ),
        )
    }
}
