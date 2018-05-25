package com.example.bakachie.jetpackproject.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.bakachie.jetpackproject.bo.Item

class DetailViewModel : ViewModel() {
    private val itemsList: MutableLiveData<List<Item>> = MutableLiveData()

    private val selected: MutableLiveData<Item> = MutableLiveData()

    fun select(position: Int) {
        selected.value = itemsList.value?.get(position)
    }

    fun selected(): LiveData<Item> {
        return selected
    }

    fun itemsList(): LiveData<List<Item>> {
        if (itemsList.value == null) {
            itemsList.value = List(30) {
                Item(it + 1)
            }
        }

        return itemsList
    }
}
