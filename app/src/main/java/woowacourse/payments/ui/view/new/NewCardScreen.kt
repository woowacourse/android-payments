package woowacourse.payments.ui.view.new

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
import woowacourse.payments.ui.component.CardChip
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.CardOwnerTextField
import woowacourse.payments.ui.component.CardPasswordTextField
import woowacourse.payments.ui.component.ExpireDateTextField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.core.CardNumberVisualTransformation
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.CardType

private const val CARD_NUMBER_GROUP_SIZE = 4
private const val CARD_SEPARATOR = " - "
private const val CARD_EXPIRE_DATE_GROUP_SIZE = 2
private const val CARD_EXPIRE_DATE_SEPARATOR = " / "

@Composable
fun NewCardScreen(
    card: Card,
    onCardChange: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {

    val focusManager = LocalFocusManager.current
    val cardNumberVisualTransformation = CardNumberVisualTransformation(
        groupSize = CARD_NUMBER_GROUP_SIZE,
        separator = CARD_SEPARATOR,
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
            maxLength = 4,
            expireDate = card.expireDate,
            groupSize = CARD_EXPIRE_DATE_GROUP_SIZE,
            separator = CARD_EXPIRE_DATE_SEPARATOR,
            onExpireDateChange = { onCardChange(card.copy(expireDate = it)) },
            onComplete = {
                focusManager.moveFocus(FocusDirection.Next)
            },
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
