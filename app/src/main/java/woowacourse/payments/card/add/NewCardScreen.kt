package woowacourse.payments.card.add

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.card.add.components.CardNumberTextField
import woowacourse.payments.card.add.components.NewCardTopBar
import woowacourse.payments.card.add.components.PaymentCard

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = 14.dp),
        ) {
            PaymentCard(modifier = Modifier.padding(top = innerPadding.calculateTopPadding()))

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
            ) {
                CardNumberTextField(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview
@Composable
fun NewCardScreenPreview() {
    NewCardScreen()
}
