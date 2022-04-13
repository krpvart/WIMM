package com.krpvartstudio.wimm.data.storage.local

import com.krpvartstudio.wimm.data.storage.models.AccountModelForStorage
import com.krpvartstudio.wimm.data.storage.models.TransactionModelForStorage
import com.krpvartstudio.wimm.domain.model.TransactionModel

interface LocalStorage {

    suspend fun addAccount(account: AccountModelForStorage)
    suspend fun deleteAccount(account: AccountModelForStorage)
    suspend fun getAccountById(idMoneyAccount: Int): AccountModelForStorage
    suspend fun getListAccount(): List<AccountModelForStorage>
    suspend fun editAccount(account: AccountModelForStorage)

    suspend fun addTransaction(transaction: TransactionModelForStorage)
    suspend fun getListTransaction(): List<TransactionModelForStorage>
    suspend fun getTransactionById(id:Int): TransactionModelForStorage

}