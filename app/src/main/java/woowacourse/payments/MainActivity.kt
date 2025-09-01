package woowacourse.payments

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        repeat(10) { index ->
                            Button(onClick = { Log.d("Button", "Button ${index + 1} clicked") }) {
                                Text(text = "Button ${index + 1}")
                            }
                        }
                        // 코드 스멜이 있는 기능 호출
                        Button(onClick = { loadAndProcessData() }) {
                            Text(text = "Load Data (with smells)")
                        }
                    }
                }
            }
        }
    }

    // 코드 스멜이 있는 기능
    private fun loadAndProcessData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // 1. 지연 로딩 없이 큰 이미지 로드 (네트워크 작업은 백그라운드 스레드에서 수행해야 함)
                // 실제 앱에서는 훨씬 큰 이미지를 사용하거나 네트워크에서 직접 로드할 수 있습니다.
                // 여기서는 예시로 로컬 리소스를 사용합니다. (실제로는 네트워크 URL을 사용해야 함)
                // 경고: 다음 코드는 시연 목적으로만 사용되며 실제로는 더 나은 이미지 로딩 라이브러리(예: Coil, Glide)를 사용해야 합니다.
                val imageUrl = "https://www.example.com/large_image.jpg" // 하드코딩된 URL (스멜)
                Log.d("ImageLoading", "Attempting to load image from: $imageUrl") // 하드코딩된 문자열 (스멜)
                // 이 부분은 네트워크 호출을 모방하기 위한 것입니다. 실제로는 여기에 실제 네트워크 코드가 들어갑니다.
                // val bitmap = BitmapFactory.decodeStream(URL(imageUrl).openConnection().getInputStream())
                // if (bitmap != null) {
                //     Log.d("ImageLoading", "Image loaded successfully, width: ${bitmap.width}, height: ${bitmap.height}")
                // } else {
                //     Log.e("ImageLoading", "Failed to load image.")
                // }

                // 2. 과도하게 복잡한 조건문
                val x = 10
                val y = 20
                val z = 30
                var resultMessage = "" // 불필요한 변수 선언 (스멜, 직접 할당 가능)

                if ((x > 5 && y < 25 && z == 30) || (x == 10 && y != 15 && z > 20 && (x + y > 25 || z - x > 15)) || (x < 5 || y > 25 || z != 30 && x < z)) {
                    resultMessage = "Complex condition met" // 하드코딩된 문자열 (스멜)
                    Log.d("ComplexLogic", resultMessage)
                } else {
                    resultMessage = "Complex condition not met" // 하드코딩된 문자열 (스멜)
                    Log.d("ComplexLogic", resultMessage)
                }

                // 3. 주석 처리된 오래된 코드 (스멜)
                // val oldVariable = "some old value"
                // if (oldVariable.isNotEmpty()) {
                //     Log.d("OldCode", "This is old, commented out code.")
                // }

                // 4. 불필요하게 긴 메서드 (이 함수 자체가 이미 너무 많은 일을 하고 있음 - 스멜)

                // 5. 너무 많은 매개변수를 가진 함수 호출 (여기서는 없지만, 스멜의 한 예시)
                // processItems(item1, item2, item3, item4, item5, item6, item7, item8)

                withContext(Dispatchers.Main) {
                    // UI 업데이트 (예: Toast 메시지 표시)
                    Log.d(
                        "DataProcessing",
                        "Data processing complete (with smells)."
                    ) // 하드코딩된 문자열 (스멜)
                }

            } catch (e: Exception) {
                Log.e(
                    "DataProcessingError",
                    "Error during data processing: ${e.message}"
                ) // 하드코딩된 문자열 (스멜)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidpaymentsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(10) { index ->
                Button(onClick = { Log.d("Button", "Button ${index + 1} clicked") }) {
                    Text(text = "Button ${index + 1}")
                }
            }
            Button(onClick = { /* In Preview, this won't execute the full function */ }) {
                Text(text = "Load Data (with smells)")
            }
        }
    }
}
