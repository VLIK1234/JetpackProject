package com.example.bakachie.jetpackproject.ui.start

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.bakachie.jetpackproject.bo.Credential

class LoginViewModel : ViewModel() {

    private val credential: MutableLiveData<Credential?> = MutableLiveData()

    fun setCredential(credential: Credential?) {
        this.credential.value = credential
    }

    fun credential() : MutableLiveData<Credential?> {
        return credential
    }
}