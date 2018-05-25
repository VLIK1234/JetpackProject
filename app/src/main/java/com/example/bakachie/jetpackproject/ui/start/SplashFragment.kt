package com.example.bakachie.jetpackproject.ui.start

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.bo.Credential
import com.example.bakachie.jetpackproject.config.Config
import com.example.bakachie.jetpackproject.utils.ActivityUtils

class SplashFragment : Fragment() {
    private val splashTimeout = 3000L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_frament, container, false)
    }

    override fun onStart() {
        super.onStart()

        if (!Config.isFirstStart) {
            ActivityUtils.close(requireActivity())
        } else {
            checkTokenAndNavigate(
                    ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java).credential.value)
            Config.isFirstStart = false
        }
    }

    private fun checkTokenAndNavigate(credential: Credential?) {
        Handler(Looper.getMainLooper()).postDelayed({
            run {
                if (TextUtils.isEmpty(credential?.token)) {
                    view?.let { Navigation.findNavController(it).navigate(R.id.loginAction) }
                } else {
                    view?.let {
                        Navigation.findNavController(it).navigate(R.id.homeAction)
                    }
                }
            }
        }, splashTimeout)
    }
}