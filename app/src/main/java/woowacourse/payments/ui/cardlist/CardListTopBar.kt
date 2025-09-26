package woowacourse.payments.ui.cardlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    cards: List<CardUiModel>,
    onAddCard: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.card_list_title)) },
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

@Preview(showBackground = true, name = "카드 목록 상단 바 (카드 1개 이하)")
@Composable
private fun CardListTopBarWithNoCardPreview() {
    AndroidpaymentsTheme {
        CardListTopBar(remember { mutableStateListOf() }) {}
    }
}

@Preview(showBackground = true, name = "카드 목록 상단 바 (카드 2개 이상)")
@Composable
private fun CardListTopBarWithTwoCardsPreview() {
    AndroidpaymentsTheme {
        CardListTopBar(
            remember {
                mutableStateListOf(
                    Card(
                        cardNumber = CardNumber("1234123412341234"),
                        expirationDate = ExpirationDate(YearMonth.of(2034, 12)),
                        cardholderName = CardholderName("디랙"),
                        passcode = Passcode("1234"),
                        cardCompany = CardCompany.BC_CARD,
                    ).toUiModel(),
                    Card(
                        cardNumber = CardNumber("1234123412341234"),
                        expirationDate = ExpirationDate(YearMonth.of(2034, 12)),
                        cardholderName = CardholderName("디랙"),
                        passcode = Passcode("1234"),
                        cardCompany = CardCompany.BC_CARD,
                    ).toUiModel(),
                )
            },
        ) {}
    }
}
