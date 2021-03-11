package com.mercadopago.px.example.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Item(
    val title: String,
    val description: String?,
    val quantity: Int,
    @SerializedName("unit_price") val unitPrice: BigDecimal
)