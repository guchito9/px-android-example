package com.mercadopago.px.example.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercadopago.px.example.Credentials
import com.mercadopago.px.example.model.Item
import com.mercadopago.px.example.repository.CheckoutPreferenceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainViewModel(
    private val checkoutPreferenceRepository: CheckoutPreferenceRepository) : ViewModel() {

    val checkoutLiveData = MutableLiveData<Pair<String, String>>()

    fun initCheckout(title: String, description: String, price: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val checkoutPreference = checkoutPreferenceRepository.create(listOf(
                Item(title, description, 1, BigDecimal(price))
            ))
            checkoutLiveData.postValue(Credentials.PUBLIC_KEY to checkoutPreference.id)
        }
    }
}