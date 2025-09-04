package woowacourse.payments.card.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.card.register.component.CardExpirationDateTextField
import woowacourse.payments.card.register.component.CardHolderNameTextField
import woowacourse.payments.card.register.component.CardNumberTextField
import woowacourse.payments.card.register.component.CardPasswordTextField
import woowacourse.payments.card.register.component.NewCardTopBar
import woowacourse.payments.card.register.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                NewCardScreen()
            }
        }
    }
}


@Preview
@Composable
private fun NewCardScreen() {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { /* 뒤로 가기 로직 */ },
                onSaveClick = { /* 저장 로직 */ },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            PaymentCard(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .align(Alignment.CenterHorizontally)
            )
            CardNumberTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            )
            CardExpirationDateTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(0.5f)

            )
            CardHolderNameTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            )
            CardPasswordTextField(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(0.5f)
            )
        }
    }
}