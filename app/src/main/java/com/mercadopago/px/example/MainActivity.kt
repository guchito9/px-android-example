package com.mercadopago.px.example

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError
import com.mercadopago.px.example.ui.main.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            when(resultCode) {
                MercadoPagoCheckout.PAYMENT_RESULT_CODE ->
                    data?.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT)?.let {
                        if (it is Payment) {
                            showToast("Payment status is: ${it.paymentStatus}")
                        }
                    }
                RESULT_CANCELED ->
                    data?.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR)?.let {
                        if (it is MercadoPagoError) {
                            showToast("There was an error: ${it.message}")
                        }
                    } ?: showToast("User canceled checkout")
            }
        }
    }

    private fun showToast(text: CharSequence) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val REQUEST_CODE = 1
    }
}