package com.krpvartstudio.wimm.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krpvartstudio.wimm.domain.model.TransactionModel
import com.krpvartstudio.wimm.domain.usecases.transactionUseCases.GetListTransactionUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TransactionListViewModel(
    private val getListTransactionUseCase: GetListTransactionUseCase
): ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineExceptionHandler { _, e -> throw e }

    private val _transactionList = MutableLiveData<List<TransactionModel>>()
    val transactionList: LiveData<List<TransactionModel>>
        get() = _transactionList

    init {
        updateTransactionList()
    }

    fun updateTransactionList(){
        viewModelScope.launch(Dispatchers.IO) {
            val transactionList = getListTransactionUseCase.getListTransaction()

            _transactionList.postValue(transactionList)
        }
    }

}