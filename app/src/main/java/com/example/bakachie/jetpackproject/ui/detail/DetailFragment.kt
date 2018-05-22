package com.example.bakachie.jetpackproject.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.ui.main.MainViewModel
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        viewModel.selected().observe(this, Observer{
            detail_label.text = getString(R.string.detail_label, it?.item)
        })
    }

}