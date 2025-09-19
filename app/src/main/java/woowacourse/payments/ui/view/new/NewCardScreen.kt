package woowacourse.payments.ui.view.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.CardChip
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.CardOwnerTextField
import woowacourse.payments.ui.component.CardPasswordTextField
import woowacourse.payments.ui.component.ExpireDateTextField
import woowacourse.payments.ui.component.NewCardName
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.core.CardNumberVisualTransformation
import woowacourse.payments.ui.core.ext.toNameResource
import woowacourse.payments.ui.state.CardCompanyState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    onFinishRequest: () -> Unit,
) {
    val newCardUiStateHolder =
        rememberSaveable(saver = NewCardUiStateHolder.Saver) {
            NewCardUiStateHolder()
        }
    val uiState = newCardUiStateHolder.uiState
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(uiState.company) {
        when (uiState.company) {
            CardCompanyState.Empty -> bottomSheetState.show()
            is CardCompanyState.Selected -> bottomSheetState.hide()
        }
    }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackClick() },
                onSaveClick = {
                    runCatching { newCardUiStateHolder.uiState.toDomain() }
                        .onSuccess { onSaveClick(it) }
                        .onFailure {
                            newCardUiStateHolder.updateCard(
                                NewCardUiEvent.OnChangeCardCompany(
                                    CardCompanyState.Empty,
                                ),
                            )
                        }
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NewCardScreen(
            uiState = newCardUiStateHolder.uiState,
            onCardChange = { event -> newCardUiStateHolder.updateCard(event) },
            modifier = Modifier.padding(innerPadding),
        )
    }

    BankSelectBottomSheet(
        modalBottomSheetState = bottomSheetState,
        onFinish = onFinishRequest,
        onCardCompanySelect = { company ->
            newCardUiStateHolder.updateCard(
                NewCardUiEvent.OnChangeCardCompany(CardCompanyState.Selected(company)),
            )
        },
    )
}

private const val CARD_NUMBER_GROUP_SIZE = 4
private const val CARD_SEPARATOR = " - "
private const val CARD_EXPIRE_DATE_GROUP_SIZE = 2
private const val CARD_EXPIRE_DATE_SEPARATOR = " / "

@Composable
fun NewCardScreen(
    uiState: NewCardUiState,
    onCardChange: (NewCardUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val cardNumberVisualTransformation =
        CardNumberVisualTransformation(
            groupSize = CARD_NUMBER_GROUP_SIZE,
            separator = CARD_SEPARATOR,
            maxLength = Card.CARD_MAX_LENGTH,
        )

    val company = (uiState.company as? CardCompanyState.Selected)?.company

    val companyName: String = company?.let { stringResource(it.toNameResource()) } ?: ""

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            card = uiState.cardState,
            content = {
                Column {
                    NewCardName(companyName)
                    CardChip()
                }
            },
            modifier =
                Modifier
                    .padding(top = 18.dp)
                    .shadow(8.dp)
                    .align(alignment = Alignment.CenterHorizontally),
        )

        CardNumberTextField(
            cardNumber = uiState.number,
            onCardNumberChange = { cardNumber ->
                onCardChange(
                    NewCardUiEvent.OnChangeCardNumber(cardNumber),
                )
            },
            onComplete = {
                focusManager.moveFocus(FocusDirection.Next)
            },
            maxLength = 16,
            visualTransformation = cardNumberVisualTransformation,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
        )

        ExpireDateTextField(
            maxLength = 4,
            expireDate = uiState.expireDate,
            groupSize = CARD_EXPIRE_DATE_GROUP_SIZE,
            separator = CARD_EXPIRE_DATE_SEPARATOR,
            onExpireDateChange = { expireDate ->
                onCardChange(NewCardUiEvent.OnChangeExpireDate(expireDate))
            },
            onComplete = {
                focusManager.moveFocus(FocusDirection.Next)
            },
            modifier =
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(top = 18.dp),
        )

        CardOwnerTextField(
            maxLength = 30,
            ownerName = uiState.ownerName,
            onChangeOwnerName = { ownerName ->
                onCardChange(NewCardUiEvent.OnChangeOwnerName(ownerName))
            },
            modifier =
                Modifier
                    .padding(top = 18.dp),
        )

        CardPasswordTextField(
            maxLength = 4,
            password = uiState.password,
            onPasswordChange = { password ->
                onCardChange(NewCardUiEvent.OnChangePassword(password))
            },
            modifier =
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(top = 18.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    NewCardScreen(
        uiState =
            NewCardUiState(
                "",
                "",
                "",
                "",
                CardCompanyState.Empty,
            ),
        onCardChange = {},
    )
}
