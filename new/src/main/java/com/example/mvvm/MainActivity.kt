package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = MainViewModel()

        binding.viewModel = mainViewModel

       mainViewModel._uiState.observe(this, Observer {
           tvTextView.text=  it.firstName
        })

        tvTextView.setOnClickListener {
            loadData()

//            tvTextView.text= mainViewModel._uiState.value?.firstName
        }

        tvTextView2.setOnClickListener {
            Log.d("getJSON ", "click...")
            Log.d("mainAct..", "before...")
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            mainViewModel.getData("https://api.sampleapis.com/codingresources/codingResources",this@MainActivity)
         }
    }

}