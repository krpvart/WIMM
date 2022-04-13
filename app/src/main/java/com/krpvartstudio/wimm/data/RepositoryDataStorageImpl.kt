package com.krpvartstudio.wimm.data

import com.krpvartstudio.wimm.data.storage.local.LocalStorage
import com.krpvartstudio.wimm.data.storage.models.AccountModelForStorage
import com.krpvartstudio.wimm.data.storage.models.TransactionModelForStorage
import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.model.TransactionModel
import com.krpvartstudio.wimm.domain.repository.RepositoryAccount
import com.krpvartstudio.wimm.domain.repository.RepositoryTransaction

class RepositoryDataStorageImpl(private val localStorage: LocalStorage) : RepositoryAccount, RepositoryTransaction {
    
    override suspend fun addAccount(account: AccountModel) {
        localStorage.addAccount(
            mapAccountToStorage(account)
        )
    }
    override suspend fun deleteAccount(account: AccountModel) {
        localStorage.deleteAccount(
            mapAccountToStorage(account)
        )
    }
    override suspend fun getAccountById(idMoneyAccount: Int): AccountModel {
        return mapAccountToDomain(
            localStorage.getAccountById(idMoneyAccount)
        )
    }
    override suspend fun getAccountList(): List<AccountModel> {
        return localStorage.getListAccount().map {mapAccountToDomain(it)}
    }
    override suspend fun editAccount(account: AccountModel) {
        localStorage.editAccount(
            mapAccountToStorage(account)
        )
    }

    override suspend fun addTransaction(transaction: TransactionModel) {
        localStorage.addTransaction(transaction = mapTransactionToStorage(transaction))
    }
    override suspend fun getTransactionList(): List<TransactionModel> {
       return localStorage.getListTransaction().map { mapTransactionToDomain(it) }
    }
    override suspend fun getTransactionById(id: Int): TransactionModel {
        return mapTransactionToDomain(localStorage.getTransactionById(id))
    }



    private fun mapAccountToStorage(accountDomain:AccountModel): AccountModelForStorage {
        return AccountModelForStorage(
            name = accountDomain.name,
            type = accountDomain.type,
            remainingFunds = accountDomain.remainingFunds,
            id = accountDomain.id
        )
    }
    private fun mapAccountToDomain(accountStorage: AccountModelForStorage): AccountModel {
        return AccountModel(
            name = accountStorage.name,
            type = accountStorage.type,
            remainingFunds = accountStorage.remainingFunds,
            id = accountStorage.id
        )
    }
    private fun mapTransactionToStorage(transaction: TransactionModel): TransactionModelForStorage{
        return TransactionModelForStorage(
            id = transaction.id,
            date = transaction.date,
            holderAccountId = transaction.holderAccountId,
            consumptionAccountId = transaction.consumptionAccountId,
            sum = transaction.sum,
            comment = transaction.comment
        )
    }
    private fun mapTransactionToDomain(transaction: TransactionModelForStorage): TransactionModel{
        return TransactionModel(
            id = transaction.id,
            date = transaction.date,
            holderAccountId = transaction.holderAccountId,
            consumptionAccountId = transaction.consumptionAccountId,
            sum = transaction.sum,
            comment =  transaction.comment
        )
    }

}