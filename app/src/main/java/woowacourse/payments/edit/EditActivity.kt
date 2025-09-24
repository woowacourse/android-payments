@file:OptIn(ExperimentalMaterial3Api::class)

package woowacourse.payments.edit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardExpiry
import woowacourse.payments.domain.CardName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.list.CardUiModel
import woowacourse.payments.list.toUiModel
import woowacourse.payments.newCard.CardCompanySelectBottomSheet
import woowacourse.payments.newCard.CardSelectionState
import woowacourse.payments.newCard.NewCardState
import woowacourse.payments.newCard.NewCardTopBar
import woowacourse.payments.ui.CardInformationForm
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.parcelable

class EditActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val card = intent.parcelable<CardUiModel>("card")
                    ?: error("Card not provided")

                val newCardState = NewCardState()
                newCardState.onNameChange(card.name ?: "")
                newCardState.onNumberChange(card.number)
                newCardState.onExpiryChange(card.expiry)
                newCardState.onPasswordChange(card.password)

                val cardSelectionState = remember { CardSelectionState() }
                cardSelectionState.selectedCompany = card.company
                cardSelectionState.isShowBottomSheet = false
                val modalBottomSheetState =
                    rememberModalBottomSheetState(
                        confirmValueChange = { false },
                    )

                if (cardSelectionState.isShowBottomSheet) {
                    CardCompanySelectBottomSheet(
                        modalBottomSheetState,
                        onClick = { cardSelectionState.selectedCompany = it.company },
                        onDismissRequest = { finish() },
                        modifier = Modifier
                    )
                }

                LaunchedEffect(key1 = cardSelectionState.selectedCompany) {
                    if (cardSelectionState.selectedCompany != CardCompany.NOT_SELECTED) {
                        cardSelectionState.isShowBottomSheet = false
                        modalBottomSheetState.hide()
                    }
                }

                Scaffold(
                    topBar = { NewCardTopBar(
                        onBackClick = {
                            finish()
                        },
                        onSaveClick = {
                            val data =
                                Intent().apply {
                                    putExtra(
                                        "card",
                                        Card(
                                            company = cardSelectionState.selectedCompany,
                                            number = CardNumber(newCardState.cardNumber),
                                            expiry = CardExpiry.fromString(newCardState.cardExpiry),
                                            password = CardPassword(newCardState.cardPassword),
                                            name = CardName(newCardState.cardName),
                                        ).toUiModel(),
                                    )
                                }
                            setResult(RESULT_OK, data)
                            finish()
                        },
                        isSaveEnabled =
                            runCatching {
                                Card(
                                    company = cardSelectionState.selectedCompany,
                                    number = CardNumber(newCardState.cardNumber),
                                    expiry = CardExpiry.fromString(newCardState.cardExpiry),
                                    password = CardPassword(newCardState.cardPassword),
                                    name = CardName(newCardState.cardName),
                                )
                            }.isSuccess,
                    ) }
                ) { innerPadding ->
                    CardInformationForm(
                        newCardState = newCardState,
                        cardSelectionState = CardSelectionState(),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    )
                }
            }
        }
    }
}
