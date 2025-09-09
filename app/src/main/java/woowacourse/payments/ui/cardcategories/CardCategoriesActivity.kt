package woowacourse.payments.ui.cardcategories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardCategoriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardCategoryTopBar(
                            onAddClick = {},
                        )
                    }) { innerPadding ->
                    CardCategoriesScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }

    companion object {
        fun instance(context: Context) = Intent(context, CardCategoriesActivity::class.java)
    }
}