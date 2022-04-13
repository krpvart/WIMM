package com.krpvartstudio.wimm.domain.usecases.accountUseCases

import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.repository.RepositoryAccount

class AddAccountUseCase(private val repositoryAccount: RepositoryAccount) {

    suspend fun addAccount(account: AccountModel){
        repositoryAccount.addAccount(account)
    }
}