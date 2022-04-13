package com.krpvartstudio.wimm.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.model.enums.AccountTypeEnum
import com.krpvartstudio.wimm.domain.usecases.accountUseCases.AddAccountUseCase
import com.krpvartstudio.wimm.domain.usecases.accountUseCases.EditAccountUseCase
import com.krpvartstudio.wimm.domain.usecases.accountUseCases.GetAccountUseCase
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MoneyAccountViewModel(
    private val addAccountUseCase: AddAccountUseCase,
    private val editAccountUseCase: EditAccountUseCase,
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel(), CoroutineScope {


    private val _moneyAccount = MutableLiveData<AccountModel>()
    val account: LiveData<AccountModel>
        get() = _moneyAccount

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineExceptionHandler { _, e -> throw e }

    fun addMoneyAccount(inputName: String?, type: AccountTypeEnum, input_remaining_funds: String) {
        val nameMoneyAccount = parseName(inputName)
        val remaining_funds = parseFunds(input_remaining_funds)
        viewModelScope.launch(Dispatchers.IO) {
            val moneyAccount =
                AccountModel(
                    name = nameMoneyAccount,
                    type = type,
                    remainingFunds = remaining_funds
                )
            addAccountUseCase.addAccount(moneyAccount)
        }
    }

    fun getMoneyAccount(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val moneyAccount = getAccountUseCase.getAccountById(id)
            _moneyAccount.postValue(moneyAccount)
        }
    }

    fun editMoneyAccount(inputName: String?, inputFunds: String?) {
        val name = parseName(inputName)
        val funds = parseFunds(inputFunds)
        _moneyAccount.value?.let {
            val moneyAccount = it.copy(name = name, remainingFunds = funds.toDouble())
            viewModelScope.launch(Dispatchers.IO) {
                editAccountUseCase.editMoneyAccount(moneyAccount)
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseFunds(inputFunds: String?): Double {
        return try {
            inputFunds?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    companion object {
        private const val ZEROBALANCE: Double = 0.0
    }


}