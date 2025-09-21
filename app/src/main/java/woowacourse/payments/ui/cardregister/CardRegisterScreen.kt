package woowacourse.payments.ui.cardregister

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.cardregister.component.CardNumberInputField
import woowacourse.payments.ui.cardregister.component.CardOwnerInputField
import woowacourse.payments.ui.cardregister.component.ExpiryDateInputField
import woowacourse.payments.ui.cardregister.component.PasswordInputField
import woowacourse.payments.ui.cards.component.SelectBankBottomSheet
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toBankType
import java.time.YearMonth

@Composable
fun CardRegisterScreen(
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
) {
    val context = LocalContext.current
    val stateHolder = remember { CardRegisterStateHolder() }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    val result =
                        Card.create(
                            cardNumber = stateHolder.cardNumber,
                            expiryDate = stateHolder.expiryDate.toYearMonth(),
                            cardOwner = stateHolder.cardOwner,
                            password = stateHolder.password,
                            bankType = stateHolder.selectedBankViewType.toBankType(),
                        )

                    result
                        .onSuccess { card ->
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.card_register_complete_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                            onSaveClick(card)
                        }.onFailure {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.card_info_invalid_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                },
            )
        },
        content = { innerPadding ->
            CardRegisterContent(innerPadding, stateHolder, onBackClick)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewCardTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(stringResource(R.string.card_register)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_desc_back),
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.content_desc_done),
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardRegisterContent(
    innerPadding: PaddingValues,
    stateHolder: CardRegisterStateHolder,
    onBackClick: () -> Unit,
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )

    LaunchedEffect(stateHolder.selectedBankViewType) {
        if (stateHolder.selectedBankViewType != BankViewType.NONE) {
            modalBottomSheetState.hide()
        }
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            bankViewType = stateHolder.selectedBankViewType,
            modifier =
                Modifier
                    .padding(top = 14.dp, bottom = 40.dp)
                    .align(Alignment.CenterHorizontally),
        )
        CardNumberInputField(
            text = stateHolder.cardNumber,
            onValueChange = { newText -> stateHolder.onCardNumberChange(newText) },
            isError = stateHolder.isCardNumberError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExpiryDateInputField(
            text = stateHolder.expiryDate,
            onValueChange = { newText -> stateHolder.onExpiryDateChange(newText) },
            isError = stateHolder.isExpiryDateError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        CardOwnerInputField(
            text = stateHolder.cardOwner,
            onValueChange = { newText -> stateHolder.onCardOwnerChange(newText) },
            isError = stateHolder.isCardOwnerError,
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordInputField(
            text = stateHolder.password,
            onValueChange = { newText -> stateHolder.onPasswordChange(newText) },
            isError = stateHolder.isPasswordError,
        )
    }
    if (stateHolder.selectedBankViewType == BankViewType.NONE) {
        SelectBankBottomSheet(
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            onBankSelectClick = {
                stateHolder.onSelectedBankViewTypeChange(it)
            },
            sheetState = modalBottomSheetState,
            onDismissRequest = onBackClick,
        )
    }
}

@Preview
@Composable
private fun ShowCardRegisterScreenPreview() {
    AndroidpaymentsTheme {
        CardRegisterScreen(
            onBackClick = { },
            onSaveClick = { },
        )
    }
}

private fun String.toYearMonth(): YearMonth? {
    val yearOffset = 2000
    if (length != 4) return null
    val year = substring(2, 4).toIntOrNull()
    val month = substring(0, 2).toIntOrNull()
    if (month !in 1..12) return null
    return if (year == null || month == null) {
        null
    } else {
        YearMonth.of(yearOffset + year, month)
    }
}
