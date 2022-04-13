package com.krpvartstudio.wimm.domain.usecases.transactionUseCases

import com.krpvartstudio.wimm.domain.model.TransactionModel
import com.krpvartstudio.wimm.domain.repository.RepositoryTransaction

class GetListTransactionUseCase(private val repositoryTransaction: RepositoryTransaction) {
    suspend fun getListTransaction(): List<TransactionModel>{
        return repositoryTransaction.getTransactionList()
    }
}