package woowacourse.payments.ui.cardupdate.components

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.R
import woowacourse.payments.ui.cardupdate.model.CardCompanyUiModel
import woowacourse.payments.ui.cardupdate.model.CardUpdateType
import woowacourse.payments.ui.common.model.CardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardUpdateTopBar(
    updateType: CardUpdateType,
    canSave: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            when (updateType) {
                CardUpdateType.Add -> Text(stringResource(R.string.add_card))
                is CardUpdateType.Edit -> Text(stringResource(R.string.edit_card))
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_description),
                )
            }
        },
        actions = {
            IconButton(
                onClick = onSaveClick,
                enabled = canSave,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.save_button_description),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CardUpdateTopBarPreview(
    @PreviewParameter(CardUpdateTopBarPreviewParameterProvider::class) updateType: CardUpdateType,
) {
    CardUpdateTopBar(
        updateType = updateType,
        canSave = true,
        onBackClick = {},
        onSaveClick = {},
    )
}

private class CardUpdateTopBarPreviewParameterProvider : PreviewParameterProvider<CardUpdateType> {
    private val cardCompany: CardCompanyUiModel =
        CardCompanyUiModel(
            name = R.string.bc_card,
            logo = R.drawable.bc,
            color = 0xFFF04651,
        )

    private val card: CardUiModel =
        CardUiModel(
            cardCompany = cardCompany,
            number = "1111222233334444",
            expirationDate = "0925",
            holderName = "CREW",
            password = "1234",
        )

    override val values: Sequence<CardUpdateType> =
        sequenceOf(
            CardUpdateType.Add,
            CardUpdateType.Edit(card = card),
        )
}
