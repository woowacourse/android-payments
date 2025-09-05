package woowacourse.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardAdditionScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardAdditionTopBar(
                onBackClick = {},
                onSaveClick = {},
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp, bottom = 28.dp),
            )
            CardNumberTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
            )
            ExpiredDateTextField(
                modifier =
                    Modifier
                        .padding(top = 18.dp),
            )
            CardOwnerNameTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 18.dp),
            )
            PasswordTextField()
        }
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview() {
    CardAdditionScreen()
}
