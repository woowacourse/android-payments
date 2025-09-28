package woowacourse.payments.ui.view.new

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.CardChip
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.CardOwnerTextField
import woowacourse.payments.ui.component.CardPasswordTextField
import woowacourse.payments.ui.component.ExpireDateTextField
import woowacourse.payments.ui.component.NewCardName
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.RegisteredCard
import woowacourse.payments.ui.core.CardNumberVisualTransformation
import woowacourse.payments.ui.core.ext.toNameResource
import woowacourse.payments.ui.preview.OneCardPreviewParameterProvider
import woowacourse.payments.ui.state.CardCompanyState
import woowacourse.payments.ui.view.new.NewCardUiStateHolder.Companion.NewCardUiStateHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    mode: NewCardMode,
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    onFinishRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val newCardUiStateHolder =
        rememberSaveable(mode, saver = NewCardUiStateHolder.Saver) {
            NewCardUiStateHolder(mode)
        }

    val uiState = newCardUiStateHolder.uiState
    val bottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val modificationGuideMessage = stringResource(R.string.card_modification_not_complete)
    val showModificationGuide: () -> Unit = {
        scope.launch {
            snackbarHostState.showSnackbar(modificationGuideMessage)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackClick() },
                onSaveClick = {
                    runCatching { newCardUiStateHolder.uiState.toDomain() }
                        .onSuccess { card ->
                            handleSaveClick(mode, uiState, card, onSaveClick, showModificationGuide)
                        }.onFailure {
                            newCardUiStateHolder.modifyUiState(
                                NewCardUiEvent.OnChangeCardCompany(
                                    CardCompanyState.Empty,
                                ),
                            )
                        }
                },
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        NewCardScreen(
            uiState = newCardUiStateHolder.uiState,
            onCardChange = { event -> newCardUiStateHolder.modifyUiState(event) },
            onClickCard = {
                newCardUiStateHolder
                    .modifyUiState(NewCardUiEvent.OnChangeBottomSheet(true))
            },
            modifier = Modifier.padding(innerPadding),
        )
    }

    if (uiState.isBottomSheetOpen) {
        BankSelectBottomSheet(
            modalBottomSheetState = bottomSheetState,
            onFinish = onFinishRequest,
            onCardCompanySelect = { company ->
                newCardUiStateHolder.modifyUiState(
                    NewCardUiEvent.OnChangeCardCompany(
                        CardCompanyState.Selected(company),
                    ),
                )
                newCardUiStateHolder.modifyUiState(
                    NewCardUiEvent.OnChangeBottomSheet(false),
                )
            },
        )
    }
}

private fun handleSaveClick(
    mode: NewCardMode,
    uiState: NewCardUiState,
    card: Card,
    onSaveClick: (Card) -> Unit,
    showModificationGuide: () -> Unit,
) {
    when (mode) {
        NewCardMode.Add -> onSaveClick(card)
        is NewCardMode.Modify -> if (uiState.isModified()) onSaveClick(card) else showModificationGuide()
    }
}

private const val CARD_NUMBER_GROUP_SIZE = 4
private const val CARD_EXPIRE_DATE_GROUP_SIZE = 2
private const val CARD_EXPIRE_DATE_SEPARATOR = " / "
private const val CARD_NUMBER_SEPARATOR = " - "
private const val CARD_MASKING_CHAR = "*"

@Composable
fun NewCardScreen(
    uiState: NewCardUiState,
    onCardChange: (NewCardUiEvent) -> Unit,
    onClickCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val cardNumberVisualTransformation =
        CardNumberVisualTransformation(
            groupSize = CARD_NUMBER_GROUP_SIZE,
            separator = CARD_NUMBER_SEPARATOR,
            maxLength = Card.CARD_MAX_LENGTH,
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            cardState = uiState.cardState,
            content = { PaymentCardContent(uiState) },
            modifier =
                Modifier
                    .padding(top = 18.dp)
                    .shadow(8.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable(onClick = onClickCard),
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
            maxLength = CARD_NUMBER_GROUP_SIZE,
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

@Composable
private fun PaymentCardContent(uiState: NewCardUiState) {
    when (uiState.mode) {
        NewCardMode.Add -> {
            when (val cardCompanyState = uiState.cardCompanyState) {
                CardCompanyState.Empty -> CardChip()

                is CardCompanyState.Selected -> {
                    Column {
                        val companyName =
                            stringResource(
                                cardCompanyState.company.toNameResource(),
                            )
                        NewCardName(companyName)
                        CardChip()
                    }
                }
            }
        }

        is NewCardMode.Modify -> {
            RegisteredCard(
                uiState.toDomain(),
                CARD_NUMBER_GROUP_SIZE,
                CARD_NUMBER_SEPARATOR,
                CARD_MASKING_CHAR,
                CARD_EXPIRE_DATE_GROUP_SIZE,
                CARD_EXPIRE_DATE_SEPARATOR,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenAddPreview() {
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
        onClickCard = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenModifyPreview(
    @PreviewParameter(OneCardPreviewParameterProvider::class) card: Card,
) {
    NewCardScreen(
        uiState =
            NewCardUiState(
                card.number,
                card.expireDate,
                card.ownerName,
                card.password,
                CardCompanyState.Selected(card.company),
            ),
        onClickCard = {},
        onCardChange = {},
    )
}
