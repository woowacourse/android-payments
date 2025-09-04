package woowacourse.payments.ui.cardcreate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.cardcreate.model.CreateCardErrorState
import woowacourse.payments.ui.cardcreate.model.CreateCardState

private val CardWidth = 208.dp
private val CardHeight = 124.dp
private val ChipWidth = 40.dp
private val ChipHeight = 26.dp
private val ChipPaddingStart = 14.dp
private val ChipPaddingBottom = 10.dp

private val CardCornerRadius = 5.dp
private val ChipCornerRadius = 4.dp
private val CardShadowElevation = 8.dp

private val ScreenAppBarSpacing = 14.dp
private val ScreenSectionSpacing = 40.dp
private val ScreenSidePadding = 24.dp

@Composable
fun CreateCardScreen(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var cardCreateState by rememberSaveable { mutableStateOf(CreateCardState()) }
    var cardErrorState by rememberSaveable { mutableStateOf(CreateCardErrorState()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding()
    ) {
        NewCardTopBar(
            onBackClick = onBackClick,
            onSaveClick = onSaveClick,
            Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(ScreenAppBarSpacing))
        PaymentCard(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(ScreenSectionSpacing))
        CreateCardInputSection(
            createCardState = cardCreateState,
            createCardErrorState = cardErrorState,
            onCardChange = { cardCreateState = it },
            onErrorChange = { cardErrorState = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ScreenSidePadding)
        )
    }
}

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(CardShadowElevation)
            .size(width = CardWidth, height = CardHeight)
            .background(
                color = colorResource(id = R.color.gray_33),
                shape = RoundedCornerShape(CardCornerRadius),
            )
    ) {
        Box(
            modifier = Modifier
                .padding(start = ChipPaddingStart, bottom = ChipPaddingBottom)
                .size(width = ChipWidth, height = ChipHeight)
                .background(
                    color = colorResource(id = R.color.yellow_CB),
                    shape = RoundedCornerShape(ChipCornerRadius),
                )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.card_create_title)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.card_create_back),
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.card_create_save),
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CardCreateScreenPreView() {
    CreateCardScreen(
        { TODO() },
        { TODO() },
    )
}