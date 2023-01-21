package com.shivchalisa
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.shivchalisa.presentation.AppTheme
import com.shivkichalisa.R

class ShowDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                intent.getStringExtra(getString(R.string.text_title))?.let {
                    ShowTopView(it)
                }

                intent.getStringExtra(getString(
                    R
                    .string.text_desc))?.let {
                    ShowShivDetail(it)
                }

            }
        }
    }

    @Composable
    private fun ShowShivDetail(data: String) {
        AppTheme(isSystemInDarkTheme()) {
            Column() {
                Text(
                    text = data,
                    style = TextStyle(color = MaterialTheme.colors.onPrimary),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState(), true)
                        .fillMaxWidth()
                        .padding(10.dp)
                        .border(1.dp, Color("#338BA8".toColorInt()))
                        .padding(12.dp)
                        .fillMaxHeight()

                )
            }
        }


    }




    @Preview
    @Composable
    private fun ShowImage() {
        Image(
            painter = painterResource(R.drawable.shiv),
            contentDescription = "Shiv Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()

        )
    }


    @Composable
    fun ShowTopView(title: String) {
        Text(
            text = title,
            color = Color("#FFFFFF".toColorInt()),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color("#338BA8".toColorInt()))
                .padding(10.dp)
                .fillMaxWidth(),

            )
    }
}