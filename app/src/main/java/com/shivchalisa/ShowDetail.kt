package com.shivchalisa

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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




import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*

class ShowDetail : ComponentActivity() {
    private lateinit var mp: MediaPlayer
    private var id: Int = 0
    private val chalisa: Int = 2
    private var playPause: Boolean = false
    var isClicked by mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()
            ) {
                intent.getIntExtra(getString(R.string.id), 0).let {
                    id = it

                }
                intent.getStringExtra(getString(R.string.text_title))?.let {
                    ShowTopView(it)
                }
                intent.getStringExtra(
                    getString(
                        R.string.text_desc
                    )
                )?.let {
                    ShowShivDetail(it)
                }
                checkIsAudio()
            }
        }
    }


    private fun checkIsAudio() {
        if (id == chalisa) {
            playSound()
        }
    }

    override fun onResume() {
        super.onResume()
        isClicked=false
        checkIsAudio()
    }


    private fun playSound() {
        mp = MediaPlayer.create(this, R.raw.shiv_aarti)
        //  mp.start()
    }

    override fun onStop() {
        super.onStop()
        if (this::mp.isInitialized) {
            mp.release()
        }

    }

    @Composable
    private fun ShowShivDetail(data: String) {
        AppTheme(isSystemInDarkTheme()) {
            Column {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    ShowImage()
                    Text(
                        text = data,
                        style = TextStyle(color = MaterialTheme.colors.onPrimary),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState(), true)
                            .fillMaxWidth()
                            .padding(10.dp)
                            .padding(12.dp)
                            .fillMaxHeight()

                    )
                }

                if (id == chalisa) {
                    IconButton(onClick = {

                        if(mp.isPlaying)
                        {
                            mp.pause()


                        }
                        else
                        {
                            mp.start()
                        }

                        isClicked=!isClicked
                    },
                        modifier = Modifier.padding(start = 10.dp), content = {
                            Image(
                                painter = painterResource(
                                    id = playPause()

                                ), contentDescription = "playPause"
                            )


                        })

                }


            }


        }


    }

    @Composable
    private fun playPause() = if (!isClicked) {
        R.drawable.ic_play
    } else {
        R.drawable.pause_icon
    }


    @Preview
    @Composable
    private fun ShowImage() {
        Image(
            painter = painterResource(R.drawable.detail_background_image),
            contentDescription = "Shiv Image",

            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.3f)
                .fillMaxHeight()

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