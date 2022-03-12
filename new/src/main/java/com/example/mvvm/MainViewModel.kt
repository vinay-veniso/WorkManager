package com.example.mvvm

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.Response
import com.example.mvvm.model.ResponseObj
import com.example.mvvm.model.User
import com.example.mvvm.repository.MainRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainViewModel : ViewModel() {

    var textView = "Welcome to my application"
    fun updateText(outPut: String) {
        textView = outPut
    }

    val _uiState: MutableLiveData<ResponseObj> = MutableLiveData<ResponseObj>()


    fun getInfo(url: String,context: Context) {
        viewModelScope.launch {
//            getData(url,context)
        }
    }


    suspend fun getData(url: String,context: Context) {

        withContext(Dispatchers.IO) {
            val response = MainRepository.fetch(url)
            Log.d("getJSON...", response.toString())

            val collectionType: Type = object : TypeToken<List<User>>() {}.type
            val usersList: List<User> = Gson().fromJson(response, collectionType) as List<User>
            Log.d("mainViewmodel",usersList.size.toString())
//            val usersList = gson.fromJson(response, Response::class.java)
             val list: MutableList<User> =mutableListOf<User>()
                for (i in usersList) {
                    list.add(i)
                }
            Log.d("mainViewmodel...", ""+list)
            list.let { AppDatabase.getInstance(context).userDao().insertAll(it) }
            val listUsers=AppDatabase.getInstance(context).userDao().getAll()
            Log.d("mainViewmodel",listUsers.get(2).types[0])
//            _uiState.postValue(gson.fromJson(response, ResponseObj::class.java))
         }
    }
}