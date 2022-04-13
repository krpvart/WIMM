package com.krpvartstudio.wimm.data.storage.local.room

import androidx.room.*

@Dao
interface RoomDao {

    //Account:

    @Query("SELECT * FROM `Account` WHERE id=:id")
    suspend fun getAccountById(id: Int): AccountEntity

    @Query("SELECT * FROM `Account`")
    suspend fun getListAccount():List<AccountEntity>

    @Insert
    suspend fun insertAccount(account: AccountEntity)

    @Update
    suspend fun updateAccount(account: AccountEntity)

    @Delete
    suspend fun deleteAccount(account: AccountEntity)


    //Transaction
    @Query("SELECT * FROM `Transaction` WHERE id=:id")
    suspend fun getTransactionById(id: Int): TransactionEntity

    @Query("SELECT * FROM `Transaction`")
    suspend fun getListTransaction(): List<TransactionEntity>

    @Insert
    suspend fun insertTransaction(transaction:TransactionEntity)



}