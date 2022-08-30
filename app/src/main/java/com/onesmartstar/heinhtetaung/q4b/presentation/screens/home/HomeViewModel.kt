package com.onesmartstar.heinhtetaung.q4b.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesmartstar.heinhtetaung.q4b.data.local.Q4BDatabase
import com.onesmartstar.heinhtetaung.q4b.data.repository.DataStoreRepository
import com.onesmartstar.heinhtetaung.q4b.data.repository.MainRepository
import com.onesmartstar.heinhtetaung.q4b.domain.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataStoreRepository: DataStoreRepository,
    dQ4BDatabase: Q4BDatabase
) : ViewModel() {

    private val itemDao = dQ4BDatabase.itemDao()
    private val _checkToken = MutableStateFlow("")
    private val checkToken: StateFlow<String> = _checkToken

    private val _state = MutableStateFlow(emptyList<Item>())
    val state: StateFlow<List<Item>>
        get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _checkToken.value = dataStoreRepository
                .readToken()
                .stateIn(viewModelScope)
                .value
        }

        viewModelScope.launch {
            itemDao.getAllItemList().collect { data ->
                _state.value = data
            }
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }

    fun getItemList() {
        viewModelScope.launch {
            try {
                itemDao.deleteAllItems()
                val itemListData = mainRepository.getAllItemList(
                    token = checkToken.value
                )
                if (itemListData.itemList.isNotEmpty()) {
                    itemDao.addItems(itemListData.itemList)
                    itemDao.getAllItemList().collect { data ->
                        _state.value = data
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}