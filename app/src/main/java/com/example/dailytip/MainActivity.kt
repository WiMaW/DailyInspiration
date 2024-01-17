package com.example.dailytip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailytip.network.DailyQuote
import com.example.dailytip.ui.theme.DailyTipTheme

class MainActivity : ComponentActivity() {

    private val viewModel: DailyViewModel by viewModels()
    private lateinit var uiState: State<DailyUiState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    viewModel.getQuotes()
                    uiState = viewModel.uiState.collectAsState()
                    DailyTipApp(uiState = uiState, viewModel, uiState.value.numberDay)
                }
            }
        }
    }
}



@Composable
fun DailyTipApp(
    uiState: State<DailyUiState>,
    viewModel: DailyViewModel,
    numberClicked: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .verticalScroll(rememberScrollState())
    ) {
        AppName()
        DayNumberButton(uiState = uiState)
        if (numberClicked == 0) {
            viewModel.dailyQuote?.let { DailyTipText(dailyQuote = it.content, dailyQuoteAuthor = it.author) }
        }
        //DailyImage(image = painterResource(dailyTip[dayNumberClick].dailyImage))
//        Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
//            dailyQuote.forEach { item ->
//                DayNumberButton(
//                    dailyTip = dailyQuote,
//                    onClick = { },
//                    text = item.dateAdded
//                )
//            }
//        }
//        DailyTipText(text = dailyQuote[dayNumberClick].content)
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
    image: Painter
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(MaterialTheme.shapes.small)
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun DayNumberButton(
    modifier: Modifier = Modifier,
    uiState: State<DailyUiState>,
) {
    LazyRow {
        items(3) {item ->
            Button(
                onClick = {uiState.value.numberDay = item},
                modifier = modifier.padding(dimensionResource(R.dimen.padding_extra_small))
            ) {
                Text(
                    text = (item + 1).toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun DailyTipText(
    modifier: Modifier = Modifier,
    dailyQuote: String,
    dailyQuoteAuthor: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .height(200.dp)
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = dailyQuote,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
        Text(
            text = "author: $dailyQuoteAuthor",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DailyTipPreview() {
//    DailyTipTheme {
//        DailyTipApp(dailyTip = dailyList)
//    }
//}
//
//@Preview
//@Composable
//fun DailyTipPreviewDark() {
//    DailyTipTheme(darkTheme = true) {
//        DailyTipApp(dailyTip = dailyList)
//    }
//}