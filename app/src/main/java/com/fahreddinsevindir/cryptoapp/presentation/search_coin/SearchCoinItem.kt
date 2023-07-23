package com.fahreddinsevindir.cryptoapp.presentation.search_coin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahreddinsevindir.cryptoapp.BuildConfig
import com.fahreddinsevindir.cryptoapp.R
import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoModel
import com.google.gson.Gson
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchCoinItem(
    coin: CryptoModel?,
    onItemClick: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (coin != null) {
                    val json = Gson().toJson(coin)
                    onItemClick.invoke(json)
                }
            },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        GlideImage(
            imageModel = BuildConfig.BASE_IMAGE_URL.plus("${coin?.id}.png"),
            contentScale = ContentScale.Fit,
            circularReveal = CircularReveal(),
            modifier = Modifier
                .size(100.dp)
                .weight(1f),
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


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.4f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = coin?.name ?: "",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
            )
            Text(
                text = coin?.symbol ?: "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 5.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.right_arrow
                ),
                contentDescription = "",
                tint = Color.Gray
            )
        }


    }

    Divider(color = Color.Gray, thickness = 0.5.dp, modifier = Modifier.padding(12.dp))
}