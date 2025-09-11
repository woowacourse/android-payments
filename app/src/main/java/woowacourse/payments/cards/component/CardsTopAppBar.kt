package woowacourse.payments.cards.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopAppBar(
    isAddActionVisible: Boolean,
    addCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                TextButton(onClick = addCard) {
                    val addActionContentDescription =
                        stringResource(R.string.cards_top_app_bar_add_action_content_description)
                    Text(
                        text = stringResource(R.string.cards_top_app_bar_add_action_message),
                        modifier =
                            Modifier
                                .semantics {
                                    contentDescription = addActionContentDescription
                                },
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                    )
                }
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
