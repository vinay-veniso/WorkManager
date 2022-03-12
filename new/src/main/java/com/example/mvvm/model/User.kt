package com.example.mvvm.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(DataConverter::class)
data class User (
    @SerializedName("id")
    @PrimaryKey
    val id : Int,
    @SerializedName("description")
    @ColumnInfo
    val description : String,
    @SerializedName("url")
    @ColumnInfo
    val url : String,

    @SerializedName("types")
    @ColumnInfo
     val types : List<String>,
    @SerializedName("topics")
    @ColumnInfo
     val topics : List<String>,
    @ColumnInfo
     @SerializedName("levels")
     val levels : List<String>
)