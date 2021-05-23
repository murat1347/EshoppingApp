package com.example.eticaretapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretapp.R
import com.example.eticaretapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_basket.*

class BasketFragment : Fragment() {

 private val productViewModel: ProductViewModel by activityViewModels()
    private var basketReceylerAdapter : BasketReceylerAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basketreceylerView.layoutManager=LinearLayoutManager(activity?.baseContext)
        productViewModel.basket.observe(viewLifecycleOwner, Observer {
            basketReceylerAdapter=BasketReceylerAdapter(it)
            basketreceylerView.adapter = basketReceylerAdapter
        })
    }

}