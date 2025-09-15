package woowacourse.payments.ui.cardcreate

import android.app.Activity.RESULT_OK
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.cards.CardsActivity
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel

private val ScreenAppBarSpacing = 14.dp
private val ScreenSectionSpacing = 40.dp
private val ScreenSidePadding = 24.dp

@Composable
fun CreateCardScreen(
    onSaveClick: (PaymentCardUiModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateHolder =
        rememberSaveable(saver = CreateCardStateHolderSaver()) { CreateCardStateHolder() }

    val onSaveHandler = remember { { onSaveClick(stateHolder.newCard()) } }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveHandler,
                isCreatable = stateHolder.isCardCreatable
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                modifier.padding(innerPadding),
        ) {
            Spacer(modifier = Modifier.height(ScreenAppBarSpacing))
            PaymentCard(null, Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(ScreenSectionSpacing))
            CreateCardInputSection(
                createCardUiState = stateHolder.cardCreateState,
                onCardNumbersChange = stateHolder::updateCardNumber,
                onCardExpiryDateChange = stateHolder::updateExpiryDate,
                onCardOwnerNameChange = stateHolder::updateOwnerName,
                onCardPasswordChange = stateHolder::updatePassword,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ScreenSidePadding),
            )
        }
    }
}

