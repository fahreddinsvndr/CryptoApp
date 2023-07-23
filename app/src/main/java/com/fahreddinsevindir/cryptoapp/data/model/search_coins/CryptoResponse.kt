package com.fahreddinsevindir.cryptoapp.data.model.search_coins

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class CryptoResponse(
    @SerializedName("data")
    val `data`: List<CryptoModel?>?,
    @SerializedName("status")
    val status: Status?
): Parcelable