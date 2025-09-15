package woowacourse.payments.ui.newcard

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.os.bundleOf
import woowacourse.payments.ui.company.model.CompanyUiModel

@Composable
fun rememberNewCardState(): NewCardState =
    rememberSaveable(saver = NewCardStateSaver) {
        NewCardState()
    }

private val NewCardStateSaver: Saver<NewCardState, Bundle> =
    Saver(
        save = { state: NewCardState ->
            bundleOf(
                "company" to state.cardCompany,
                "number" to state.cardNumber,
                "expirationDate" to state.cardExpirationDate,
                "holderName" to state.cardHolderName,
                "password" to state.cardPassword,
            )
        },
        restore = { bundle: Bundle ->
            NewCardState().apply {
                bundle.getParcelable<CompanyUiModel>("company")?.let { onCompanySelected(it) }
                bundle.getString("number")?.let { onCardNumberChange(it) }
                bundle.getString("expirationDate")?.let { onCardExpirationDateChange(it) }
                bundle.getString("holderName")?.let { onCardHolderNameChange(it) }
                bundle.getString("password")?.let { onCardPasswordChange(it) }
            }
        },
    )
