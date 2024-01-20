package com.example.dailytip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dailytip.ui.theme.DailyInspirationTheme


class MainActivity : ComponentActivity() {

    private val viewModel: DailyViewModel by viewModels()
    private lateinit var uiState: State<DailyUiState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyInspirationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    viewModel.getQuotes()
                    val imageUri = viewModel.getImage()
                    uiState = viewModel.uiState.collectAsState()
                    DailyInspirationApp(viewModel, uiState, imageUri)
                }
            }
        }
    }
}

@Composable
fun DailyInspirationApp(
    viewModel: DailyViewModel,
    uiState: State<DailyUiState>,
    imageUri: String,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_small))
            .verticalScroll(rememberScrollState())
    ) {
        AppName()
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        when (uiState.value.numberClicked) {
            0 -> viewModel.dailyQuote?.let {
                DailyTipText(
                    dailyQuote = it.content,
                    dailyQuoteAuthor = it.author
                )
            }

            1 -> DailyImage(imageUrl = imageUri)

        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        DayNumberButton(viewModel)
        Box(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_large)),
            Alignment.BottomCenter
        ) {
            if (uiState.value.networkProblems) SnackBarConnectionProblems()
        }

    }
}

@Composable
fun SnackBarConnectionProblems(
) {
    Snackbar(
        shape = MaterialTheme.shapes.small,
    ) {
        Text(text = "Network problem. Try again.")
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
                )
            }
        })
}

@Composable
fun DailyImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .height(260.dp)
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(MaterialTheme.shapes.small)
        )
    }
}

@Composable
fun DayNumberButton(
    viewModel: DailyViewModel,
    modifier: Modifier = Modifier
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(2) { item ->
            Button(
                onClick = { viewModel.updateView(item) },
                modifier = modifier
                    .width(150.dp),
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = if (item == 0) "quote" else "image",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
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
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = "\"$dailyQuote\"",
            style = MaterialTheme.typography.titleMedium,
            lineHeight = 30.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                .padding(vertical = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = "author: $dailyQuoteAuthor",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                .padding(vertical = dimensionResource(R.dimen.padding_small))
        )
    }
}