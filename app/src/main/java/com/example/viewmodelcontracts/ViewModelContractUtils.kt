package com.example.viewmodelcontracts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

const val PARENT_VM_KEY = "parent_vm"

@Suppress("UNCHECKED_CAST")
fun <T> Fragment.parentViewModel(): T {
    val clazz: Class<ViewModel> = arguments?.getSerializable(PARENT_VM_KEY) as Class<ViewModel>

    val viewModelProvider = parentFragment?.let { parent ->
        ViewModelProvider(parent)
    } ?: ViewModelProvider(requireActivity())

    return viewModelProvider.get(clazz) as T
}

fun <T> registerParentViewModel(parent: Class<T>): Bundle {
    return Bundle().apply {
        putSerializable(PARENT_VM_KEY, parent)
    }
}