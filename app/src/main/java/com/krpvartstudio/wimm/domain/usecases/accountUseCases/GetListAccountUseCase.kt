package com.krpvartstudio.wimm.domain.usecases.accountUseCases

import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.repository.RepositoryAccount

class GetListAccountUseCase(private val repositoryAccount: RepositoryAccount) {
    suspend fun getListAccount():List<AccountModel>{
        return repositoryAccount.getAccountList()
    }
}