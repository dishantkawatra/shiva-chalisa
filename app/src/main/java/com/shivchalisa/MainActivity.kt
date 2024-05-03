package com.shivchalisa
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.shivchalisa.model.EmployDetails
import com.shivchalisa.model.ModelDataClass
import com.shivkichalisa.R



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                showView()
                showImage()
                AdmobBanner(modifier = Modifier.fillMaxWidth())
                ShowListItem()


            }

        }

    }
    @Preview
    @Composable
    fun AdmobBanner(modifier: Modifier = Modifier) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                // on below line specifying ad view.
                AdView(context).apply {
                    // on below line specifying ad size
                    //adSize = AdSize.BANNER
                    // on below line specifying ad unit id
                    // currently added a test ad unit id.
                    setAdSize(AdSize.BANNER)
                    adUnitId = context.getString(R.string.ad_unit)
                    // calling load ad to load our ad.
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }


    @Composable
     fun showView() {
        Text(
            text = stringResource(R.string.text_toolbar_title),
            color = Color("#FFFFFF".toColorInt()),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color("#338BA8".toColorInt()))
                .padding(10.dp)
                .fillMaxWidth(),

            )
    }



    @Composable
    fun ListItemView(emp: EmployDetails) {
        Card(
            elevation = 4.dp, backgroundColor = Color("#338BA6".toColorInt()), modifier = Modifier
                .padding(1.5.dp)
                .clickable { showDetails(emp.description, emp.title, emp.id) }
                .fillMaxWidth()
                .height(45.dp)
        ) {

            Column(verticalArrangement = Arrangement.Center)
            {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
                    Text(text = emp.title, modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically), textAlign = TextAlign.Center, style = typography.h6, color = Color("#FFFFFF".toColorInt()))
                    if(emp.id==2)
                    {
                        Image(painter = painterResource(R.drawable.ic_audio),
                            contentDescription = "Shiv Image",
                            alignment = Alignment.CenterEnd,
                            contentScale = ContentScale.Crop,

                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .align(Alignment.CenterVertically)
                                .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)

                        )
                    }

                }

            }


        }


    }
    @Preview
    @Composable
    fun ShowListItem() {
        val puppies = remember { ModelDataClass.EmployDetailsList }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        ) {
            items(
                items = puppies,
                itemContent = {
                    ListItemView(emp = it)
                })
        }
    }


    private fun showDetails(description: String, title: String, id: Int)
    {
        val i = Intent(this@MainActivity, ShowDetail::class.java)
        i.putExtra(getString(R.string.text_desc), description)
        i.putExtra(getString(R.string.text_title), title)
        i.putExtra(getString(R.string.id), id)
        startActivity(i)
    }


    @Preview
    @Composable
    private fun showImage() {
        Image(
            painter = painterResource(R.drawable.shiv),
            contentDescription = "Shiv Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()

        )
    }
}