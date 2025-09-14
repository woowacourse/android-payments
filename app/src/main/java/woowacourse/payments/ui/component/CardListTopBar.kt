package woowacourse.payments.ui.component

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
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    showAddButton: Boolean,
    onAddClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.card_list_top_bar_title),
                fontWeight = FontWeight.Normal,
            )
        },
        actions = {
            if (showAddButton) {
                TextButton(onClick = onAddClick) {
                    Text(
                        text = stringResource(R.string.card_list_top_bar_add_card),
                        modifier =
                            Modifier.semantics {
                                contentDescription = "카드 목록 상단 추가 텍스트"
                            },
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
    )
}

class ShowAddButtonProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}

@Composable
@Preview(showBackground = true)
fun CardListTopBarPreview(
    @PreviewParameter(ShowAddButtonProvider::class) showAddButton: Boolean,
) {
    AndroidpaymentsTheme {
        CardListTopBar(
            showAddButton = showAddButton,
            onAddClick = {},
        )
    }
}
