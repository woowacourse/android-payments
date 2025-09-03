package woowacourse.payments.ui.addcard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PlaceholderTransformation(
    private val placeholder: String,
    private val textColor: Color
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(
                text = placeholder,
                spanStyle = SpanStyle(color = textColor),
            ), AddCardOffsetMapping
        )
    }
}

private object AddCardOffsetMapping : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return 0
    }

    override fun transformedToOriginal(offset: Int): Int {
        return 0
    }
}