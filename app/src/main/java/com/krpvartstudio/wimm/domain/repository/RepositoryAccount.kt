package com.krpvartstudio.wimm.domain.repository

import com.krpvartstudio.wimm.domain.model.AccountModel

interface RepositoryAccount {
    suspend fun addAccount(account: AccountModel)
    suspend fun deleteAccount(account: AccountModel)
    suspend fun getAccountById(id: Int): AccountModel
    suspend fun getAccountList(): List<AccountModel>
    suspend fun editAccount(account: AccountModel)
}