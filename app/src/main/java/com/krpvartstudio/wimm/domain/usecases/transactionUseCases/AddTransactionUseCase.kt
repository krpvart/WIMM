package com.krpvartstudio.wimm.domain.usecases.transactionUseCases

import com.krpvartstudio.wimm.domain.model.TransactionModel
import com.krpvartstudio.wimm.domain.repository.RepositoryTransaction

class AddTransactionUseCase(private val repositoryTransaction: RepositoryTransaction) {
    suspend fun addTransaction(transactionModel: TransactionModel){
        repositoryTransaction.addTransaction(transactionModel)
    }
}