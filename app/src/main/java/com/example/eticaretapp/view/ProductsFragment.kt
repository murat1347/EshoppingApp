package com.example.eticaretapp.view

import ProductRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eticaretapp.R
import com.example.eticaretapp.model.Product
import com.example.eticaretapp.service.ProductAPI
import com.example.eticaretapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsFragment : Fragment(), ProductRecyclerAdapter.Listener {
    private val productViewModel: ProductViewModel by activityViewModels()
    //private var job : Job? = null
    //private var productList: ArrayList<Product>? = null
    private var productRecyclerAdapter : ProductRecyclerAdapter?=null

    //Shared ViewModel



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView.layoutManager = GridLayoutManager(activity?.baseContext,2)

        productViewModel.downloadData()
        productViewModel.productList.observe(viewLifecycleOwner, Observer {
            productRecyclerAdapter = ProductRecyclerAdapter(it,this)
            recyclerView.adapter = productRecyclerAdapter
        })

        //downloadData()
    }
/*
    private fun downloadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI::class.java)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofit.getData()
            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {
                        productList = ArrayList(it)
                        productList?.let {
                            productRecyclerAdapter = ProductRecyclerAdapter(it)
                            recyclerView.adapter = productRecyclerAdapter
                        }
                    }
                }
            }
        }
    }
 */

    override fun onDestroyView() {
        super.onDestroyView()
        //job?.cancel()
    }

    override fun onItemClick(product: Product) {
      //  productViewModel.addToBasket(product)

        productViewModel.basket.observe(viewLifecycleOwner, Observer {
            it.forEach {
                println(it.name)
            }
        })
    }


}