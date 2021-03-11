package com.mercadopago.px.example.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.px.example.MainActivity
import com.mercadopago.px.example.R
import com.mercadopago.px.example.repository.CheckoutPreferenceRepository

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var price: EditText
    private lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            title = findViewById(R.id.title)
            configInput(title)
            description = findViewById(R.id.description)
            configInput(description)
            price = findViewById(R.id.price)
            configInput(price)
            button = findViewById(R.id.button)
            button.setOnClickListener {
                button.isEnabled = false
                viewModel.initCheckout(title.text.toString(), description.text.toString(), price.text.toString())
            }
        }
        viewModel = MainViewModel(CheckoutPreferenceRepository()).also {
            it.checkoutLiveData.observe(viewLifecycleOwner, { checkoutData ->
                activity?.let { activity ->
                    button.isEnabled = true
                    MercadoPagoCheckout.Builder(checkoutData.first, checkoutData.second)
                        .build()
                        .startPayment(activity, MainActivity.REQUEST_CODE)
                }
            })
        }
    }

    private fun configInput(input: EditText) {
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button.isEnabled = title.text.isNotBlank() && description.text.isNotBlank() && price.text.isNotBlank()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}