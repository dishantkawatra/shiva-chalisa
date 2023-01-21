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
import androidx.core.graphics.toColorInt
import com.shivchalisa.model.EmployDetails
import com.shivchalisa.model.ModelDataClass
import com.shivkichalisa.R



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                ShowView()
                ShowImage()
                BarkHomeContent()

            }

        }

    }


    @Composable
     fun ShowView() {
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
    fun PuppyListItem(emp: EmployDetails) {
        Card(
            elevation = 6.dp, backgroundColor = Color("#338BA8".toColorInt()), modifier = Modifier
                .padding(10.dp)
                .clickable { openNewData(emp.description,emp.title) }
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = emp.title, style = typography.h6, color = Color("#FFFFFF".toColorInt()))

            }
        }


    }

    @Composable
    fun BarkHomeContent() {
        val puppies = remember { ModelDataClass.EmployDetailsList }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        ) {
            items(
                items = puppies,
                itemContent = {
                    PuppyListItem(emp = it)
                })
        }
    }


    private fun openNewData(description: String, title: String)
    {
        val i = Intent(this@MainActivity, ShowDetail::class.java)
        i.putExtra(getString(R.string.text_desc), description)
        i.putExtra(getString(R.string.text_title), title)
        startActivity(i)
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
}