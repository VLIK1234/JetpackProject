package com.example.bakachie.jetpackproject.ui.start

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.bo.Credential

class SplashFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_frament, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java)
//        viewModel.setCredential(viewModel.credential().value)
//        viewModel.credential().observe(this, Observer {
//            checkTokenAndNavigate(it)
//        })

        checkTokenAndNavigate(viewModel.credential().value)
    }

    private fun checkTokenAndNavigate(credential: Credential?) {
        if (credential?.token == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                run {
                    view?.let { Navigation.findNavController(it).navigate(R.id.loginAction)  }
                }
            }, 3000L)
        } else {
            view?.let { Navigation.findNavController(it).navigate(R.id.homeAction)  }
        }
    }
}