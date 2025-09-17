package woowacourse.payments.ui.cards.core.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import woowacourse.payments.ui.model.ImageSource

@Composable
fun ImageSource.asPainter(): Painter =
    when (this) {
        is ImageSource.Resource -> painterResource(id)
    }
