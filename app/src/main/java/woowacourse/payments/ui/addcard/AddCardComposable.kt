package woowacourse.payments.ui.addcard

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.component.CardExpirationDateVisualTransformation
import woowacourse.payments.ui.component.CardNumberVisualTransformation
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Dimens.AddCardComposableComponentPadding
import woowacourse.payments.ui.theme.Dimens.AddCardComposableScreenPadding
import woowacourse.payments.ui.theme.Dimens.FIELD_HALF_WIDTH

@Composable
fun GenerateCardView(modifier: Modifier = Modifier) {
    var cardDetails by remember { mutableStateOf(Card()) }

    AndroidpaymentsTheme {
        Scaffold(
            topBar = { NewCardTopBar(onBackClick = {}, onSaveClick = {}) },
            modifier = modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.Start,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(AddCardComposableScreenPadding),
            ) {
                PaymentCard(
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally),
                )
                CardNumber(
                    cardDetails.number,
                    onValueChange = { input ->
                        cardDetails =
                            cardDetails.copy(number = cardDetails.number.onValueChange(input))

                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = AddCardComposableComponentPadding),
                )
                EndDate(
                    modifier =
                        Modifier
                            .fillMaxWidth(FIELD_HALF_WIDTH),
                    onValueChange = { input ->
                        cardDetails =
                            cardDetails.copy(
                                expirationDate = cardDetails.expirationDate.onValueChange(input),
                            )
                    },
                    endDate = cardDetails.expirationDate,
                )
                CardOwner(
                    cardOwner = cardDetails.ownerName,
                    Modifier
                        .fillMaxWidth(),
                    onValueChange = { input ->
                         cardDetails = cardDetails.copy(ownerName = cardDetails.ownerName.onValueChange(input))

                    },
                )
                Password(
                    password = cardDetails.password,
                    onValueChange = { input ->
                        cardDetails = cardDetails.copy(password = cardDetails.password.onValueChange(input))
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth(FIELD_HALF_WIDTH),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GenerateCardPreview() {
    GenerateCardView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text("카드 추가") },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "완료",
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
fun PaymentCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
    }
}

@Composable
fun CardNumber(
    cardNumber: CardNumber,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = cardNumber.toString(),
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        placeholder = { Text("0000-0000-0000-0000") },
        label = { Text("카드 번호") },
        supportingText = {
            Text(" ")
        },
        visualTransformation = CardNumberVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}

@Composable
fun EndDate(
    endDate: CardExpirationDate,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = endDate.toString(),
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text("MM / YY") },
        label = { Text("만료일") },
        isError = endDate.isValid().not(),
        supportingText = {
            Text(" ")
        },
        visualTransformation = CardExpirationDateVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}

@Composable
fun CardOwner(
    cardOwner: OwnerName,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = cardOwner.toString(),
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
        label = { Text("카드 소유자 이름(선택)") },
        singleLine = true,
        supportingText = {
            Text(
                "${cardOwner.toString().length}/30",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}

@Composable
fun Password(
    password: Password,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = password.toString(),
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text("0000") },
        label = { Text("비밀번호") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}
