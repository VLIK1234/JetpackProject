package com.example.bakachie.jetpackproject.ui.start

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.bakachie.jetpackproject.App
import com.example.bakachie.jetpackproject.bo.Credential

class LoginViewModel : ViewModel() {

    val credential: MutableLiveData<Credential?> = MutableLiveData()

    init {
        val token : String? = App.prefs?.token
        token?.let { credential.value = Credential(it) }
    }
}