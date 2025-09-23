package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.newcard.state.CardStateHolder
import java.time.YearMonth


@Composable
fun NewCardScreen(
    navigateToBack: () -> Unit,
    onSaveClick: (Card?) -> Unit,
    card: Card? = null,
    modifier: Modifier = Modifier
) {
    val stateHolder = CardStateHolder()
    stateHolder.changeCard(card)
    Scaffold(
        modifier = modifier,
        topBar = {
            NewCardTopBar(
                stateHolder = stateHolder,
                card = card,
                onBackClick = { navigateToBack() },
                onSaveClick = { onSaveClick(stateHolder.newCard()) }
            )
        }) { paddingValues: PaddingValues ->
        if (stateHolder.uiState.isBottomSheetOpen) {
            SelectedBankBottomSheet(
                stateHolder = stateHolder,
            )
        }
        NewCardColumn(
            stateHolder = stateHolder,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
private fun NewCardScreenPreview1() {
    val card = Card(
        cardCompany = CardCompany.BC,
        number = CardNumber("1234567890123456"),
        ownerName = OwnerName("Hwang Chaewon"),
        expiredDate = ExpiredDate(YearMonth.now().plusYears(1)),
        password = Password("1234")
    )
    NewCardScreen({}, {}, card)
}
