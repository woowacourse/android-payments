package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.CardCompanySelectBottomSheet
import woowacourse.payments.ui.component.CardExpiryDate
import woowacourse.payments.ui.component.CardHolderName
import woowacourse.payments.ui.component.CardNumber
import woowacourse.payments.ui.component.CardPassword
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (CardUiModel) -> Unit = {},
) {
    var cardNumber by remember { mutableStateOf("") }
    var cardExpiryDate by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var cardPassword by remember { mutableStateOf("") }

    var selectedCardCompany by rememberSaveable { mutableStateOf<CardCompanyUiModel?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        showBottomSheet = true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackClick() },
                onSaveClick = {
                    onSaveClick(
                        CardUiModel(
                            cardNumber = cardNumber,
                            cardHolderName = cardHolderName,
                            cardExpiryDate = cardExpiryDate,
                            cardPassword = cardPassword,
                            cardCompanyUiModel = selectedCardCompany,
                        ),
                    )
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally),
                cardCompany = selectedCardCompany,
                onCompanyClick = { showBottomSheet = true },
            )

            Spacer(modifier = Modifier.height(40.dp))
            CardNumber(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier =
                    Modifier
                        .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(30.dp))
            CardExpiryDate(
                value = cardExpiryDate,
                onValueChange = { cardExpiryDate = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )

            Spacer(modifier = Modifier.height(30.dp))
            CardHolderName(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(10.dp))
            CardPassword(
                value = cardPassword,
                onValueChange = { cardPassword = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
        if (showBottomSheet) {
            CardCompanySelectBottomSheet(
                onCompanyClick = { company ->
                    selectedCardCompany = company
                    showBottomSheet = false
                },
                onDismissRequest = {
                    showBottomSheet = false
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
