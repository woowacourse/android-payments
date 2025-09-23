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
import woowacourse.payments.ui.newcard.state.CardStateHolder
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.Gray79

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    stateHolder: CardStateHolder,
    card: Card?,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            if (card != null) {
                Text(stringResource(R.string.edit_card_title))
            } else
                Text(stringResource(R.string.add_card_title))
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
                    tint = if (stateHolder.uiState.isPossibleAddCard) Black else Gray79
                )
            }
        },
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun NewCardTopBarPreview() {

    NewCardTopBar(stateHolder = CardStateHolder(), card = null, onBackClick = {}, onSaveClick = {})
}
