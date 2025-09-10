package woowacourse.payments.cards.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopAppBar(
    isAddActionVisible: Boolean,
    addCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val addActionContentDescription =
        stringResource(R.string.cards_top_app_bar_add_action_content_description)

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.cards_top_app_bar_title),
                fontSize = 22.sp,
            )
        },
        modifier = modifier,
        actions = {
            if (isAddActionVisible) {
                Text(
                    text = stringResource(R.string.cards_top_app_bar_add_action_message),
                    modifier =
                        Modifier
                            .padding(
                                horizontal = 20.dp,
                                vertical = 9.dp,
                            )
                            .semantics {
                                contentDescription = addActionContentDescription
                            }
                            .clickable {
                                addCard()
                            },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                )
            }
        },
    )
}

@Preview
@Composable
private fun CardsTopAppBarPreview(
    @PreviewParameter(CardsTopAppBarPreviewParameterProvider::class) isAddActionVisible: Boolean,
) {
    CardsTopAppBar(
        isAddActionVisible = isAddActionVisible,
        addCard = {},
    )
}

private class CardsTopAppBarPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}
