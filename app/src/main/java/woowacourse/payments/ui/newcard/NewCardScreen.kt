package woowacourse.payments.ui.newcard

import android.R.attr.password
import android.util.Log.e
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.Card.Companion.Card
import woowacourse.payments.ui.newcard.component.NewCardColumn
import woowacourse.payments.ui.newcard.component.NewCardTopBar

@Composable
fun NewCardScreen(
    navigateToBack: () -> Unit,
    onSaveClick: (Card) -> Unit,
    modifier: Modifier = Modifier
) {
    var number by rememberSaveable { mutableStateOf("") }
    var expirationDate by rememberSaveable { mutableStateOf("") }
    var ownerName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var numberErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    var expirationDateErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    var ownerNameErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    val newCardUiState = NewCardUiState(
        number = number,
        expirationDate = expirationDate,
        ownerName = ownerName,
        password = password,
    )

    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            NewCardTopBar(
                onBackClick = { navigateToBack() },
                onSaveClick = {
                    runCatching {
                        Card(
                            number = number,
                            expirationDate = expirationDate,
                            ownerName = ownerName,
                            password = password
                        )
                    }.onSuccess { card ->
                        onSaveClick(card)
                        Toast.makeText(context, "카드가 추가되었습니다", Toast.LENGTH_SHORT).show()
                    }.onFailure { e ->
                        Toast.makeText(context, e.message ?: "카드가 추가되지 않았습니다", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            )
        }) { paddingValues: PaddingValues ->
        NewCardColumn(
            newCardUiState = newCardUiState,
            onNumberChange = { number = it.removeSurrounding(" - ").take(16) },
            onExpirationDateChange = { expirationDate = it.removeSurrounding(" / ").take(4) },
            onOwnerNameChange = { ownerName = it.take(30) },
            onPasswordChange = { password = it.take(4) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun NewCardScreenPreview1() {
    NewCardScreen({}, {})
}