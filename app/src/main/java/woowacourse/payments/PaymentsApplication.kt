package woowacourse.payments

import android.app.Application
import woowacourse.payments.cards.CardsViewModel

class PaymentsApplication : Application() {
    val cardsViewModel = CardsViewModel()
}
