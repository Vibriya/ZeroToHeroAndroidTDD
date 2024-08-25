package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import ru.easycode.zerotoheroandroidtdd.ui.theme.Typography
import ru.easycode.zerotoheroandroidtdd.ui.theme.ZeroToHeroAndroidTDDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroToHeroAndroidTDDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scene()
                }
            }
        }
    }
}

@Composable
fun Scene() {
    val showButton: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    val label: String = if (showButton.value) "Click me!" else "That's right!"
    ElevatedButton(
        onClick = {
            showButton.value = !showButton.value
        },
        modifier = Modifier.wrapContentSize()
    ) {
        Text(label)
    }
}

@Preview(widthDp = 360, heightDp = 720, showBackground = true)
@Composable
fun PreviewView() {
    ZeroToHeroAndroidTDDTheme {
        Scene()
    }
}