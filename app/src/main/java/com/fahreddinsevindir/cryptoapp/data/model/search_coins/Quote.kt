package com.fahreddinsevindir.cryptoapp.data.model.search_coins

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Quote(
    @SerializedName("USD")
    val uSD: USD?
): Parcelable