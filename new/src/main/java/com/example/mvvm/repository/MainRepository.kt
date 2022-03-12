package com.example.mvvm.repository

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MainRepository {



    fun fetch(url:String):String {
        val obj = URL(url)
        val con = obj.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        val responseCode = con.responseCode
        println("Response Code :: $responseCode")
        if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            val `in` =
                BufferedReader(InputStreamReader(con.inputStream))
            var inputLine: String?
            val response = StringBuffer()
            while (`in`.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            `in`.close()
            Log.d("mainAct...",response.toString())
            return response.toString()
//                   response.toString()
        } else {
          return  ""
        }
    }
}