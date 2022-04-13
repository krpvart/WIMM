package com.krpvartstudio.wimm.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.model.TransactionModel
import com.krpvartstudio.wimm.domain.model.enums.AccountTypeEnum
import com.krpvartstudio.wimm.domain.usecases.accountUseCases.GetListAccountUseCase
import com.krpvartstudio.wimm.domain.usecases.transactionUseCases.AddTransactionUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val getListAccountUseCase: GetListAccountUseCase,
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineExceptionHandler { _, e -> throw e }

    private val _accountHolderList = MutableLiveData<List<AccountModel>>()
    val accountHolderList: LiveData<List<AccountModel>>
        get() = _accountHolderList

    private val _accountConsuptionList = MutableLiveData<List<AccountModel>>()
    val accountConsuptionList: LiveData<List<AccountModel>>
        get() = _accountConsuptionList

    init {
        viewModelScope.launch(Dispatchers.IO) { updateList() }
    }

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
             val accountList = getListAccountUseCase.getListAccount()
            _accountHolderList.postValue(
                accountList.filter { it.type == AccountTypeEnum.HOLDER })
            _accountConsuptionList.postValue(
                accountList.filter { it.type == AccountTypeEnum.CONSUMPTION })
        }
    }

    fun addTransaction(date: Long, fromAccountId: Int, toAccountId:Int, sum: Double, comment: String?){
        val transaction = TransactionModel(date = date,holderAccountId = fromAccountId, consumptionAccountId = toAccountId, sum = sum, comment = comment)
        viewModelScope.launch(Dispatchers.IO) {
            addTransactionUseCase.addTransaction(transaction)
        }

    }

}