package com.krpvartstudio.wimm.domain.usecases.accountUseCases

import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.repository.RepositoryAccount

class GetAccountUseCase(private val repositoryAccount: RepositoryAccount) {
    suspend fun getAccountById(id:Int): AccountModel {
        return repositoryAccount.getAccountById(id)
    }
}