package com.krpvartstudio.wimm.domain.usecases.accountUseCases

import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.repository.RepositoryAccount

class DeleteAccountUseCase(private val repositoryAccount: RepositoryAccount) {
    suspend fun deleteMoneyAccount(account: AccountModel){
        repositoryAccount.deleteAccount(account)
    }
}