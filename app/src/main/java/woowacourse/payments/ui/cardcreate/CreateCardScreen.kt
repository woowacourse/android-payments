package woowacourse.payments.ui.cardcreate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.model.paymentCardUiModelSample

private val ScreenAppBarSpacing = 14.dp
private val ScreenSectionSpacing = 40.dp
private val ScreenSidePadding = 24.dp

@Composable
fun CreateCardScreen(
    stateHolder: CreateCardStateHolder,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(ScreenAppBarSpacing))
        PaymentCard(null, Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(ScreenSectionSpacing))
        CreateCardInputSection(
            createCardUiState = stateHolder.cardCreateState,
            onCardNumbersChange = stateHolder::updateCreateCardNumbers,
            onCardExpiryDateChange = stateHolder::updateCreateCardExpiryDate,
            onCardOwnerNameChange = stateHolder::updateCreateCardOwnerName,
            onCardPasswordChange = stateHolder::updateCreateCardPassword,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ScreenSidePadding),
        )
    }
}
