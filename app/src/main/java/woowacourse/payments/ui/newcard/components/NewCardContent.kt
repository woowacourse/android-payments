package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.newcard.state.NewCardContentUiState

@Composable
fun NewCardContent(
    newCardContentUiState: NewCardContentUiState,
    onClickCardSample: () -> Unit,
    onCardNumbersChange: (String) -> Unit,
    onCardExpiryDateChange: (String) -> Unit,
    onCardOwnerNameChange: (String) -> Unit,
    onCardPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier,
    ) {
        CardSample(
            newCardContentUiState.bankUiModel,
            Modifier
                .align(Alignment.CenterHorizontally)
                .clickable(onClick = onClickCardSample),
        )
        Spacer(modifier = Modifier.height(40.dp))
        NewCardInputSection(
            newCardContentUiState = newCardContentUiState,
            onCardNumbersChange = onCardNumbersChange,
            onCardExpiryDateChange = onCardExpiryDateChange,
            onCardOwnerNameChange = onCardOwnerNameChange,
            onCardPasswordChange = onCardPasswordChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
        )
    }
}