package woowacourse.payments.ui.cardform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.data.CardRepositoryImpl
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardExpirationErrorCode
import woowacourse.payments.domain.CardRepository
import woowacourse.payments.ui.cardform.component.CardCompanySelectBottomSheet
import woowacourse.payments.ui.cardform.component.CardExpirationDateTextField
import woowacourse.payments.ui.cardform.component.CardFormTopAppBar
import woowacourse.payments.ui.cardform.component.CardNumberTextField
import woowacourse.payments.ui.cardform.component.CardPasswordTextField
import woowacourse.payments.ui.cardform.component.CardholderNameTextField
import woowacourse.payments.ui.cardform.state.CardAction
import woowacourse.payments.ui.cardform.state.CardFormScreenUiState
import woowacourse.payments.ui.cardform.state.CardFormStateHolder
import woowacourse.payments.ui.cardform.state.CardFormViewModel
import woowacourse.payments.ui.common.component.PaymentCard
import woowacourse.payments.ui.common.component.toMessageResource
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardFormScreen(
    cardAction: CardAction,
    onBackPressed: () -> Unit,
    onSaveClick: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardFormViewModel = remember { CardFormViewModel(CardRepositoryImpl()) },
) {
    val originCard =
        when (cardAction) {
            CardAction.Register -> CardUiModel()
            is CardAction.Modify -> viewModel.loadCard(cardId = cardAction.cardId)
        }

    val stateHolder =
        rememberSaveable(saver = CardFormStateHolder.Saver) {
            when (cardAction) {
                CardAction.Register -> CardFormStateHolder()
                is CardAction.Modify ->
                    CardFormStateHolder(
                        uiState =
                            CardFormScreenUiState(
                                card = originCard,
                                isBottomSheetOpen = false,
                            ),
                    )
            }
        }

    Scaffold(
        topBar = {
            CardFormTopAppBar(
                title =
                    when (cardAction) {
                        CardAction.Register -> stringResource(R.string.card_form_top_app_bar_registration_title)
                        is CardAction.Modify -> stringResource(R.string.card_form_top_app_bar_modify_title)
                    },
                onBackClick = onBackPressed,
                onSaveClick = { onSaveClick(stateHolder.uiState.card) },
                isSaveButtonEnabled =
                    when (cardAction) {
                        CardAction.Register -> stateHolder.isRegistrableCard
                        is CardAction.Modify ->
                            stateHolder.isRegistrableCard &&
                                viewModel.isModify(originCard, stateHolder.uiState.card)
                    },
            )
        },
    ) { innerPadding ->
        if (stateHolder.uiState.isBottomSheetOpen) {
            CardCompanySelectBottomSheet(
                cardCompanies = viewModel.loadCardCompanies(),
                onCardCompanyClick = { cardCompany: CardCompanyUiModel ->
                    stateHolder.updateBottomSheetVisible(false)
                    stateHolder.updateCardCompany(cardCompany)
                },
                onDismissRequest =
                    { stateHolder.updateBottomSheetVisible(false) },
            )
        }

        Column(
            modifier =
                modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { stateHolder.updateBottomSheetVisible(true) },
                card = stateHolder.uiState.card,
            )

            Spacer(modifier = Modifier.height(40.dp))

            CardNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = stateHolder.uiState.card.cardNumberUiModel,
                onCardNumberChanged = { number: String ->
                    stateHolder.updateCardNumber(number = number)
                },
            )

            Spacer(modifier = Modifier.height(24.dp))

            CardExpirationDateTextField(
                cardExpirationDate = stateHolder.uiState.card.cardExpirationDateUiModel,
                onCardExpirationDateChanged = { expirationDate: String ->
                    stateHolder.updateCardExpirationDate(expirationDate)
                    val errorCode: CardExpirationErrorCode? =
                        viewModel.validateCardExpirationDate(expirationDate)
                    errorCode?.let { stateHolder.updateExpirationDateErrorMessage(errorCode.toMessageResource()) }
                },
                errorMessage =
                    stateHolder.cardExpirationErrorMessageResource?.let { stringResource(it) },
            )

            Spacer(modifier = Modifier.height(12.dp))

            CardholderNameTextField(
                modifier = Modifier.fillMaxWidth(),
                cardholderName = stateHolder.uiState.card.cardholderNameUiModel,
                onCardholderNameChanged = { name: String ->
                    stateHolder.updateCardholderName(name)
                },
            )

            Spacer(modifier = Modifier.height(12.dp))

            CardPasswordTextField(
                cardPassword = stateHolder.uiState.card.cardPasswordUiModel,
                onCardPasswordChanged = { password: String ->
                    stateHolder.updatePassword(password)
                },
            )
        }
    }
}

@Preview(showBackground = true, name = "카드 추가")
@Composable
private fun RegisterPreview() {
    AndroidpaymentsTheme {
        CardFormScreen(
            cardAction = CardAction.Register,
            onSaveClick = {},
            onBackPressed = {},
        )
    }
}

@Preview(showBackground = true, name = "카드 수정")
@Composable
private fun ModifyPreview() {
    val viewModel =
        CardFormViewModel(
            cardRepository =
                CardFormFixture(
                    listOf(
                        Card(
                            1L,
                            "CN",
                            "1111222233334444",
                            "1234",
                            CardCompany.HANA,
                            CardExpirationDate.from(CardExpirationDate.toCardExpirationDateStatus("1199")),
                        ),
                    ),
                ),
        )
    AndroidpaymentsTheme {
        CardFormScreen(
            cardAction = CardAction.Modify(1L),
            onSaveClick = {},
            onBackPressed = {},
            viewModel = viewModel,
        )
    }
}

private data class CardFormFixture(
    val cards: List<Card>,
) : CardRepository {
    override fun findAll(): List<Card> = cards

    override fun add(card: Card) {}

    override fun update(
        cardId: Long,
        updateCard: Card,
    ) {
    }

    override fun findById(cardId: Long): Card = cards.first { it.id == cardId }
}
