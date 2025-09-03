package woowacourse.payments.presentation.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.presentation.newcard.component.CardNumberTextField
import woowacourse.payments.presentation.newcard.component.ExpiredDateTextField
import woowacourse.payments.presentation.newcard.component.NewCardTopBar
import woowacourse.payments.presentation.newcard.component.OwnerNameTextField
import woowacourse.payments.presentation.newcard.component.PasswordTextField
import woowacourse.payments.presentation.newcard.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
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
                            onSaveClick = { },
                        )
                    }) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        PaymentCard(modifier = Modifier.padding(top = 14.dp))

                        Column(
                            verticalArrangement = Arrangement.spacedBy(30.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 24.dp, horizontal = 40.dp)
                        ) {
                            CardNumberTextField(modifier = Modifier.fillMaxWidth())
                            ExpiredDateTextField()
                            OwnerNameTextField(modifier = Modifier.fillMaxWidth())
                            PasswordTextField()
                        }
                    }
                }
            }
        }
    }
}
