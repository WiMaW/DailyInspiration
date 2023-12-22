package com.example.dailytip

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailytip.Data.DailyTip
import com.example.dailytip.Data.dailyList
import com.example.dailytip.ui.theme.DailyTipTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import java.lang.Integer.parseInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ){
                    DailyTipApp(dailyTip = dailyList)
                }
            }
        }
    }
}

@Composable
fun DailyTipApp(
    dailyTip: List<DailyTip>,
    modifier: Modifier = Modifier
){
    var dayNumberClick by remember { mutableStateOf(value = 0)}

        Column (
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .verticalScroll(rememberScrollState())
        ){
            AppName()
            DailyImage(image = painterResource(dailyTip[dayNumberClick].dailyImage))
            Row (modifier = modifier.horizontalScroll(rememberScrollState())){
                dailyTip.forEach {item ->
                    DayNumberButton(
                        dailyTip = dailyList,
                        onClick = { dayNumberClick = parseInt(item.dayNumber) - 1},
                        text = item.dayNumber
                    )
                }
            }
            DailyTipText(text = stringResource(dailyTip[dayNumberClick].tip))
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppName() {
    CenterAlignedTopAppBar(
        title = {
        Row {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(top = 30.dp)
            )
        }
    })
}

@Composable
fun DailyImage(
    modifier: Modifier = Modifier,
    image: Painter){
    Box(modifier = modifier
        .fillMaxWidth()
        .height(250.dp)
        .clip(MaterialTheme.shapes.small)){
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
    }
}

@Composable
fun DayNumberButton(
    dailyTip: List<DailyTip>,
    onClick: () ->Unit,
    text: String,
    modifier: Modifier = Modifier){
        Button(
            onClick = onClick,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_extra_small))
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

@Composable
fun DailyTipText(text: String, modifier: Modifier = Modifier){
    Box (contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .height(200.dp)
            .padding(dimensionResource(R.dimen.padding_small))
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DailyTipPreview() {
    DailyTipTheme {
        DailyTipApp(dailyTip = dailyList)
    }
}

@Preview
@Composable
fun DailyTipPreviewDark() {
    DailyTipTheme (darkTheme = true){
        DailyTipApp(dailyTip = dailyList)
    }
}