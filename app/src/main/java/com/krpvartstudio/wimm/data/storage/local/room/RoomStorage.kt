package com.krpvartstudio.wimm.data.storage.local.room

import com.krpvartstudio.wimm.app.App
import com.krpvartstudio.wimm.data.storage.local.LocalStorage
import com.krpvartstudio.wimm.data.storage.models.AccountModelForStorage
import com.krpvartstudio.wimm.data.storage.models.TransactionModelForStorage


class RoomStorage : LocalStorage {

    private val db by lazy { App.db }
    private val dbAccess = db.getDao()

    override suspend fun addAccount(account: AccountModelForStorage) {
        val accountEntity = accountEntityFromStorageModel(account)
        dbAccess.insertAccount(accountEntity)
    }

    override suspend fun deleteAccount(account: AccountModelForStorage) {
        val accountEntity = accountEntityFromStorageModel(account)
        dbAccess.deleteAccount(accountEntity)
    }

    override suspend fun getAccountById(idAccount: Int): AccountModelForStorage {
        return dbAccess.getAccountById(idAccount).toAccountStogareModel()
    }

    override suspend fun getListAccount(): List<AccountModelForStorage> {
        return dbAccess.getListAccount().map { it.toAccountStogareModel() }
    }

    override suspend fun editAccount(account: AccountModelForStorage) {
        val accountEntity = accountEntityFromStorageModel(account)
        dbAccess.updateAccount(accountEntity)
    }

    override suspend fun addTransaction(transaction: TransactionModelForStorage) {
       val transactionEntity = transactionEntityFromStorageModel(transaction)
        dbAccess.insertTransaction(transactionEntity)
    }

    override suspend fun getListTransaction(): List<TransactionModelForStorage> {
        return dbAccess.getListTransaction().map { it.toTransactionStorageModel()}
    }

    override suspend fun getTransactionById(id: Int): TransactionModelForStorage {
        TODO("Not yet implemented")
    }


    private fun accountEntityFromStorageModel(account: AccountModelForStorage): AccountEntity {
        return AccountEntity(
            id = account.id,
            name = account.name,
            type = account.type.toString(),
            remainingFunds = account.remainingFunds
        )
    }

    private fun transactionEntityFromStorageModel(transaction: TransactionModelForStorage): TransactionEntity{
        return TransactionEntity(
            id = transaction.id,
            date = transaction.date,
            holderAccountId = transaction.holderAccountId,
            consumptionAccountId = transaction.consumptionAccountId,
            sum = transaction.sum,
            comment = transaction.comment
        )
    }

}