package com.krpvartstudio.wimm.domain.repository

import com.krpvartstudio.wimm.domain.model.TransactionModel

interface RepositoryTransaction {
    suspend fun addTransaction(transaction: TransactionModel)
    suspend fun getTransactionList(): List<TransactionModel>
    suspend fun getTransactionById(id:Int): TransactionModel
}