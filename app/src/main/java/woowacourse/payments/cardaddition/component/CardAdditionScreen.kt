package woowacourse.payments.cardaddition.component

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.Card
import woowacourse.payments.EXTRA_CARD
import woowacourse.payments.cardaddition.CardAdditionUiState
import woowacourse.payments.ui.component.PaymentCard

@Composable
fun CardAdditionScreen(
    state: CardAdditionUiState,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onHolderChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val activity: Activity? = LocalActivity.current
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.testTag("CardAdditionScreen"),
        topBar = {
            CardAdditionTopAppBar(
                completable = state.isValid,
                onBackClick = { activity?.finish() },
                onSaveClick = {
                    activity?.setResult(
                        RESULT_OK,
                        Intent().putExtra(
                            EXTRA_CARD,
                            Card(
                                number = state.cardNumber,
                                owner = state.holder,
                                expiredDate = state.expiredDate,
                            ),
                        ),
                    )

                    activity?.finish()
                },
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp, bottom = 28.dp),
            )
            CardNumberTextField(
                value = state.cardNumber,
                onValueChange = onCardNumberChange,
                isError = state.cardNumber.isNotBlank() && !state.isValidCardNumber,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
            )
            ExpiredDateTextField(
                value = state.expiredDate,
                onValueChange = onExpiredDateChange,
                isError = state.expiredDate.isNotBlank() && !state.isValidExpiredDate,
                modifier =
                    Modifier
                        .padding(top = 18.dp),
            )
            CardOwnerNameTextField(
                value = state.holder,
                onValueChange = onHolderChange,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 18.dp),
                maxLength = state.holderMaxLength,
            )
            PasswordTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                isError = state.password.isNotBlank() && !state.isValidPassword,
            )
        }
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview() {
    CardAdditionScreen(
        state = CardAdditionUiState(),
        onCardNumberChange = {},
        onExpiredDateChange = {},
        onHolderChange = {},
        onPasswordChange = {},
        modifier = Modifier.fillMaxSize(),
    )
}
