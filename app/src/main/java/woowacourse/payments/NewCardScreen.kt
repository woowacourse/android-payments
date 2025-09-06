package woowacourse.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.component.CardChip
import woowacourse.payments.component.CardNumberTextField
import woowacourse.payments.component.CardOwnerTextField
import woowacourse.payments.component.CardPasswordTextField
import woowacourse.payments.component.ExpireDateTextField
import woowacourse.payments.component.PaymentCard
import woowacourse.payments.core.CardNumberVisualTransformation
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardType

@Composable
fun NewCardScreen(
    card: Card,
    onCardChange: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {

    val focusManager = LocalFocusManager.current
    val cardNumberVisualTransformation = CardNumberVisualTransformation(
        groupSize = Card.CARD_NUMBER_GROUP_SIZE,
        separator = Card.CARD_SEPARATOR,
        maxLength = Card.CARD_MAX_LENGTH,
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            cardType = CardType.PENDING,
            content = { CardChip() },
            modifier = Modifier
                .padding(top = 18.dp)
                .shadow(8.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        CardNumberTextField(
            cardNumber = card.number,
            onCardNumberChange = { onCardChange(card.copy(number = it)) },
            onComplete = {
                focusManager.moveFocus(FocusDirection.Next)
            },
            maxLength = 16,
            visualTransformation = cardNumberVisualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp)
        )

        ExpireDateTextField(
            expireDate = card.expireDate,
            onExpireDateChange = { onCardChange(card.copy(expireDate = it)) },
            onComplete = {
                focusManager.moveFocus(FocusDirection.Next)
            },
            maxLength = 4,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 18.dp)
        )

        CardOwnerTextField(
            maxLength = 30,
            ownerName = card.ownerName,
            onChangeOwnerName = { onCardChange(card.copy(ownerName = it)) },
            modifier = Modifier
                .padding(top = 18.dp)
        )

        CardPasswordTextField(
            maxLength = 4,
            password = card.password,
            onPasswordChange = { onCardChange(card.copy(password = it)) },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 18.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    NewCardScreen(
        card = Card.EMPTY,
        onCardChange = {},
    )
}
