package com.shilpa.codecheck.repository.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieInfo : Parcelable {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("director")
    @Expose
    var director: String? = null
    @SerializedName("producer")
    @Expose
    var producer: String? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
    @SerializedName("rt_score")
    @Expose
    var rtScore: String? = null
    @SerializedName("people")
    @Expose
    var people: List<String>? = null
    @SerializedName("species")
    @Expose
    var species: List<String>? = null
    @SerializedName("locations")
    @Expose
    var locations: List<String>? = null
    @SerializedName("vehicles")
    @Expose
    var vehicles: List<String>? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(String::class.java!!.getClassLoader()) as String
        this.title = `in`.readValue(String::class.java!!.getClassLoader()) as String
        this.description = `in`.readValue(String::class.java!!.getClassLoader()) as String
        this.director = `in`.readValue(String::class.java!!.getClassLoader()) as String
        this.producer = `in`.readValue(String::class.java!!.getClassLoader()) as String
        this.releaseDate = `in`.readValue(String::class.java!!.getClassLoader()) as String
        this.rtScore = `in`.readValue(String::class.java!!.getClassLoader()) as String
        //        in.readList(this.people, (java.lang.String.class.getClassLoader()));
        //        in.readList(this.species, (java.lang.String.class.getClassLoader()));
        //        in.readList(this.locations, (java.lang.String.class.getClassLoader()));
        //        in.readList(this.vehicles, (java.lang.String.class.getClassLoader()));
        // this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param title
     * @param releaseDate
     * @param species
     * @param locations
     * @param rtScore
     * @param description
     * @param producer
     * @param vehicles
     * @param director
     * @param people
     * @param url
     */
    constructor(id: String, title: String, description: String, director: String, producer: String, releaseDate: String, rtScore: String, people: List<String>, species: List<String>, locations: List<String>, vehicles: List<String>, url: String) : super() {
        this.id = id
        this.title = title
        this.description = description
        this.director = director
        this.producer = producer
        this.releaseDate = releaseDate
        this.rtScore = rtScore
        this.people = people
        this.species = species
        this.locations = locations
        this.vehicles = vehicles
        this.url = url
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(title)
        dest.writeValue(description)
        dest.writeValue(director)
        dest.writeValue(producer)
        dest.writeValue(releaseDate)
        dest.writeValue(rtScore)
        dest.writeList(people)
        dest.writeList(species)
        dest.writeList(locations)
        dest.writeList(vehicles)
        dest.writeValue(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieInfo> {
        override fun createFromParcel(parcel: Parcel): MovieInfo {
            return MovieInfo(parcel)
        }

        override fun newArray(size: Int): Array<MovieInfo?> {
            return arrayOfNulls(size)
        }
    }

}
