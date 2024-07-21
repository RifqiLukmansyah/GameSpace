package com.rifqi.gamespace.data.model

import android.os.Parcel
import android.os.Parcelable


data class Game(
    var name: String = "",
    var description: String = "",
    var developerName: String = "",
    var releaseDate: String = "",
    var posterImage: Int = 0,
    var backgroundImage: Int = 0,
    var categories: String = "",
    var rating: Double = 0.0,
    var totalReviews: Int = 0,
    var price: Int = 0,
    var link: String = "",
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(developerName)
        parcel.writeString(releaseDate)
        parcel.writeInt(posterImage)
        parcel.writeInt(backgroundImage)
        parcel.writeString(categories)
        parcel.writeDouble(rating)
        parcel.writeInt(totalReviews)
        parcel.writeInt(price)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}