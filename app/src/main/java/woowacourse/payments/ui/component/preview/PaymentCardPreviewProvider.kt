package woowacourse.payments.ui.component.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.mapper.toUiModel

class PaymentCardPreviewProvider : PreviewParameterProvider<PaymentCardUiModel> {
    override val values: Sequence<PaymentCardUiModel>
        get() =
            sequenceOf(
                PaymentCardUiModel(
                    id = "",
                    cardNumber = "1234567812345678",
                    expiry = "0511",
                    owner = "minjeong",
                    bank = BankUiModel.PlaceHolder,
                ),
                PaymentCardUiModel(
                    id = "",
                    cardNumber = "1234567812345678",
                    expiry = "0511",
                    owner = "junseo",
                    bank = BankType.KB.toUiModel(),
                ),
            )
}
