package com.example.eticaretapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eticaretapp.model.Product
import com.example.eticaretapp.service.ProductAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {
    private var job: Job?=null
    val productList= MutableLiveData<List<Product>>()
    val basket = MutableLiveData<List<Product>>()
    val totalBasket = MutableLiveData<Int>()

    fun downloadData(){
        val retrofit= Retrofit.Builder()

            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI::class.java)

    job= viewModelScope.launch(context = (Dispatchers.IO)){
        val response=retrofit.getData()
        withContext(Dispatchers.Main){
            if(response.isSuccessful){
                response.body()?.let {
                    productList.value=it
                     }
                }
            }
        }

    }

    override fun onCleared(){
        super.onCleared()
        job?.cancel()

    }

}