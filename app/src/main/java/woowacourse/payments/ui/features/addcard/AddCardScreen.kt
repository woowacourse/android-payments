package woowacourse.payments.ui.features.addcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.features.addcard.components.CardNumberField
import woowacourse.payments.ui.features.addcard.components.NewCardTopBar
import woowacourse.payments.ui.features.addcard.components.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(
    onNavigateBack: () -> Unit,
    onNavigateSave: () -> Unit,
) {
    val text: MutableState<String> = remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onNavigateBack,
                onSaveClick = onNavigateSave,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            PaymentCard()
            Spacer(modifier = Modifier.height(40.dp))
            CardNumberField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen({},{})
    }
}
