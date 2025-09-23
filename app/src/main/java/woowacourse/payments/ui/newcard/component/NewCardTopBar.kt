package woowacourse.payments.ui.newcard.component

import android.R.attr.navigationIcon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.state.CardStateHolder
import woowacourse.payments.ui.newcard.state.NewCardStatus
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.Gray79
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    newCardStatus: NewCardStatus,
    isPossibleAddCard: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            when (newCardStatus) {
                is NewCardStatus.CreateCard -> Text(stringResource(R.string.add_card_title))
                is NewCardStatus.EditCard -> Text(stringResource(R.string.edit_card_title))
            }
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_description_icon_back),
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.content_description_icon_check),
                    tint = if (isPossibleAddCard) Black else Gray79
                )
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true, name = "카드 추가")
@Composable
private fun NewCardTopBarPreview1() {
    NewCardTopBar(
        newCardStatus = NewCardStatus.CreateCard,
        isPossibleAddCard = false,
        onBackClick = {},
        onSaveClick = {},
    )
}

@Preview(showBackground = true, name = "카드 수정")
@Composable
private fun NewCardTopBarPreview2() {
    val card = CardUiModel(
        number = "1234567890123456",
        ownerName = "Hwang Chaewon",
        expiredDate = "0230",
        password = "1234",
        cardCompanyUiModel = CardCompanyUiModel.Default,
    )
    NewCardTopBar(
        newCardStatus = NewCardStatus.EditCard(cardUiModel = card),
        isPossibleAddCard = false,
        onBackClick = {},
        onSaveClick = {},
    )
}
