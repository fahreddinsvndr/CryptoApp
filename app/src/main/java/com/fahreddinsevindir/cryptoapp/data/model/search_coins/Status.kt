package com.fahreddinsevindir.cryptoapp.data.model.search_coins


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Status(
    @SerializedName("credit_count")
    val creditCount: Int?,
    @SerializedName("elapsed")
    val elapsed: Int?,
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("error_message")
    val errorMessage: String?,
    @SerializedName("notice")
    val notice: String?,
    @SerializedName("timestamp")
    val timestamp: String?,
    @SerializedName("total_count")
    val totalCount: Int?
): Parcelable