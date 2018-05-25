package com.example.bakachie.jetpackproject.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bakachie.jetpackproject.R
import com.example.bakachie.jetpackproject.config.Config
import com.example.bakachie.jetpackproject.ui.detail.DetailViewModel
import com.example.bakachie.jetpackproject.ui.start.StartActivityArgs
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), ItemAdapter.ItemClickListener {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel::class.java)

        if (Config.isFirstStart) {
            firstStartApp()
        } else {
            item_list.adapter = ItemAdapter(viewModel.itemsList().value!!, this)
            item_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun firstStartApp() {
        view?.let {
            val bundle = StartActivityArgs.Builder().setDeeplinkUri(requireActivity().intent.dataString).build().toBundle()
            Navigation.findNavController(it).navigate(R.id.startAction, bundle)
            requireActivity().finishAffinity()
        }
    }

    override fun onItemClick(item: View) {
        viewModel.select(item.tag as Int)
        Navigation.findNavController(item).navigate(R.id.detail_action)
    }
}
