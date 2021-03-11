package com.mercadopago.px.example.service

import androidx.annotation.Size
import com.mercadopago.px.example.model.Item
import com.mercadopago.px.example.model.Payer

data class CheckoutPreferenceBody(
    @Size(min = 1) val items: List<Item>,
    val payer: Payer = Payer()
)