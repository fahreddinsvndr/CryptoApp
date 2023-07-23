package com.fahreddinsevindir.cryptoapp.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahreddinsevindir.cryptoapp.BuildConfig
import com.fahreddinsevindir.cryptoapp.common.componet.LineChart
import com.fahreddinsevindir.cryptoapp.ui.theme.Orj
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),

    ) {

    val state = viewModel.state.value
    val data = listOf(
        Pair("1h", state.cryptoDetail?.quote?.uSD?.percentChange1h),
        Pair("24h", state.cryptoDetail?.quote?.uSD?.percentChange24h),
        Pair("30d", state.cryptoDetail?.quote?.uSD?.percentChange30d),
        Pair("60d", state.cryptoDetail?.quote?.uSD?.percentChange60d),
        Pair("7d", state.cryptoDetail?.quote?.uSD?.percentChange7d),
        Pair("90d", state.cryptoDetail?.quote?.uSD?.percentChange90d),

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally
    ) {
        GlideImage(
            imageModel = BuildConfig.BASE_IMAGE_URL.plus("${state.cryptoDetail?.id}.png"),
            contentScale = ContentScale.Fit,
            circularReveal = CircularReveal(),
            modifier = Modifier
                .size(100.dp),
            failure = {
                Text(text = "Yüklenirken Bir Sorun Oluştu", textAlign = TextAlign.Center)
            },
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = state.cryptoDetail?.name?.toUpperCase() ?: "",
            style = MaterialTheme.typography.headlineLarge,
            fontStyle = FontStyle.Italic,
            color = Orj,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.size(24.dp))

        LineChart(
            data = data as List<Pair<String, Double>>,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(CenterHorizontally)
        )

    }
}