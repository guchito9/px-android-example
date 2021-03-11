package com.mercadopago.px.example.repository

import com.mercadopago.px.example.Credentials
import com.mercadopago.px.example.model.CheckoutPreference
import com.mercadopago.px.example.model.Item
import com.mercadopago.px.example.service.CheckoutPreferenceBody
import com.mercadopago.px.example.service.CheckoutPreferenceService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheckoutPreferenceRepository {

    private val checkoutPreferenceService: CheckoutPreferenceService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        checkoutPreferenceService = retrofit.create(CheckoutPreferenceService::class.java)
    }

    suspend fun create(items: List<Item>): CheckoutPreference {
        return checkoutPreferenceService.create(CheckoutPreferenceBody(items), Credentials.PRIVATE_KEY)
    }

    companion object {
        private const val BASE_URL = "https://api.mercadopago.com"
    }
}