package com.example.bakachie.jetpackproject.ui.start

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.bo.Credential
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener({
            ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java).setCredential(Credential("success"))
            Navigation.createNavigateOnClickListener(R.id.loginResultAction).onClick(view)
        })
    }
}