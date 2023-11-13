package com.wiprotest.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class University(
    @SerializedName("alpha_two_code" ) var alphaTwoCode : String? = null,
    @SerializedName("country" ) val country: String? = null,
    @SerializedName("domains" ) val domains: List<String>? = null,
    @SerializedName("name" ) val name: String? = null,
    @SerializedName("state-province" ) val stateProvince: String? = null,
    @SerializedName("web_pages" ) val webPages: List<String>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(alphaTwoCode)
        parcel.writeString(country)
        parcel.writeStringList(domains)
        parcel.writeString(name)
        parcel.writeString(stateProvince)
        parcel.writeStringList(webPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<University> {
        override fun createFromParcel(parcel: Parcel): University {
            return University(parcel)
        }

        override fun newArray(size: Int): Array<University?> {
            return arrayOfNulls(size)
        }
    }

}