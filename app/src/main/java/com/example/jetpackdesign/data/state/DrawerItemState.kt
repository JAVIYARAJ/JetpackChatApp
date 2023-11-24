package com.example.jetpackdesign.data.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.model.common.DrawerItemModel

class DrawerItemState(private val drawerItems: List<DrawerItemModel>) {

    private val _drawerItem = drawerItems.toMutableStateList()
    val drawerItem: List<DrawerItemModel> = _drawerItem

    fun changeTabIndex(title: String) {
        drawerItems.forEach {
            it.isActiveIcon = it.title == title
        }
    }

}

class StateViewModel : ViewModel() {
    private val _item = MutableLiveData<List<DrawerItemModel>>()
    val items: LiveData<List<DrawerItemModel>> = _item

    init {
        _item.postValue(FakeData.DRAWER_ITEM)
    }

    fun onTabChange(title: String) {
        _item.value?.forEach {
            it.isActiveIcon = it.title == title
        }
    }
}