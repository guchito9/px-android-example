package com.mercadopago.px.example.service

import com.mercadopago.px.example.model.CheckoutPreference
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface CheckoutPreferenceService {

    @POST("/checkout/preferences")
    suspend fun create(
        @Body body: CheckoutPreferenceBody,
        @Query("access_token") privateKey: String
    ): CheckoutPreference
}