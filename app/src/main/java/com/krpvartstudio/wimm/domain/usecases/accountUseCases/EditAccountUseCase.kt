package com.krpvartstudio.wimm.domain.usecases.accountUseCases

import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.repository.RepositoryAccount

class EditAccountUseCase(private val repositoryAccount: RepositoryAccount) {
    suspend fun editMoneyAccount(account: AccountModel){
        repositoryAccount.editAccount(account)
    }
}