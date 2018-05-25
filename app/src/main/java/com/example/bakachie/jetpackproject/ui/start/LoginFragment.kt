package com.example.bakachie.jetpackproject.ui.start

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bakachie.jetpackproject.App
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.ui.main.MainActivityArgs
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener({
            if ("admin" == username.text.toString() && "admin" == password.text.toString()) {
                val token = "success"
                App.prefs?.token = token
                Navigation.findNavController(view).navigate(R.id.loginResultAction, mainActivityBundle())
            } else {
                username.error = "Wrong username/password."
            }
        })
    }

    private fun mainActivityBundle(): Bundle? {
        val startActivityArgs = StartActivityArgs.fromBundle(requireActivity().intent.extras)
        return MainActivityArgs.Builder().setDeeplinkUri(startActivityArgs.deeplinkUri).build().toBundle()
    }
}