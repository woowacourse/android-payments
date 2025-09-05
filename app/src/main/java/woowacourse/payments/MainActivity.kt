package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardTopBar(
                            onBackClick = { },
                            onSaveClick = {},
                        )
                    },
                ) { innerPadding ->
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                    ) {
                        Spacer(modifier = Modifier.height(14.dp))
                        PaymentCard(
                            modifier =
                                Modifier
                                    .align(Alignment.CenterHorizontally),
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        DigitTextField(
                            label = getString(R.string.card_number_label),
                            hint = "0000 - 0000 - 0000 - 0000",
                            modifier = Modifier.padding(horizontal = 24.dp),
                            maxLength = 16,
                            mask = InputMask.CardNumber,
                            errorMessage = getString(R.string.card_number_error_message),
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        DigitTextField(
                            label = getString(R.string.card_expiry_label),
                            hint = "MM / YY",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(horizontal = 24.dp),
                            maxLength = 4,
                            mask = InputMask.Expiry,
                            errorMessage = getString(R.string.card_expiry_error_message),
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        LimitedTextField(
                            label = getString(R.string.card_owner_label),
                            hint = getString(R.string.card_owner_hint),
                            modifier = Modifier.padding(horizontal = 24.dp),
                            maxLength = 30,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DigitTextField(
                            label = getString(R.string.card_password_label),
                            hint = "0000",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(horizontal = 24.dp),
                            maxLength = 4,
                            mask = InputMask.Password,
                            errorMessage = getString(R.string.card_password_error_message),
                        )
                    }
                }
            }
        }
    }
}
