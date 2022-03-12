package com.example.mvvm.model

import android.util.Log
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type

class DataConverter {

    @TypeConverter
    fun fromTypesList(types: List<String>?): String? {
        Log.d("mainViewmodel..",types.toString())

        if (types == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>?>() {}.type
        Log.d("mainViewmodel..","fromTypesList")
        return gson.toJson(types, type)
    }

    @TypeConverter
    fun toTypesList(types: String?): List<String>? {
        Log.d("mainViewmodel..",types.toString())

        if (types == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String>>(types, type)
    }
}