package woowacourse.payments.ui.allcards.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.allcards.model.AllCardsUiState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AllCardsTopbar(
    allCards: AllCardsUiState,
    modifier: Modifier = Modifier,
    onPlusCardClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(R.string.payments_allcards_topbar_all_cards))
        },
        actions = {
            when (allCards.viewType) {
                AllCardsUiState.ViewType.MULTIPLE -> {
                    Text(
                        modifier =
                            Modifier
                                .padding(end = 16.dp)
                                .clickable {
                                    onPlusCardClick()
                                },
                        text = stringResource(R.string.payments_allcards_topbar_add_cards),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }

                AllCardsUiState.ViewType.SINGLE -> Unit
                AllCardsUiState.ViewType.EMPTY -> Unit
            }
        },
    )
}
