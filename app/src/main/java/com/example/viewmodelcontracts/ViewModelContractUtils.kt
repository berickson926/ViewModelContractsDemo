package com.example.viewmodelcontracts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

private const val VIEW_MODEL_CONTRACT_KEY = "view_model_contract_key"

@Suppress("UNCHECKED_CAST")
fun <T> Fragment.viewModelContract(): T {
    val clazz: Class<ViewModel> = arguments?.getSerializable(VIEW_MODEL_CONTRACT_KEY) as Class<ViewModel>

    val viewModelProvider = parentFragment?.let { parent ->
        ViewModelProvider(parent)
    } ?: ViewModelProvider(requireActivity())

    return viewModelProvider.get(clazz) as T
}

fun <T> registerViewModelContract(viewModelContract: Class<T>): Bundle {
    return Bundle().apply {
        putSerializable(VIEW_MODEL_CONTRACT_KEY, viewModelContract)
    }
}