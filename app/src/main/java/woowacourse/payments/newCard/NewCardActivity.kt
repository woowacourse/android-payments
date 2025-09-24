package woowacourse.payments.newCard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardExpiry
import woowacourse.payments.domain.CardName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.list.toUiModel
import woowacourse.payments.ui.CardInformationForm
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val newCardState = NewCardState()
                val cardSelectionState = remember { CardSelectionState() }
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
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardTopBar(
                            title = { Text(text = stringResource(R.string.add_new_card_top_bar_title))  },
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
                        )
                    },
                ) { innerPadding ->
                    CardInformationForm(
                        newCardState = newCardState,
                        cardSelectionState = cardSelectionState,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                    )
                }
            }
        }
    }
}
