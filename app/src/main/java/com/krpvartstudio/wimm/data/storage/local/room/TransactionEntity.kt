package com.krpvartstudio.wimm.data.storage.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.krpvartstudio.wimm.data.storage.models.TransactionModelForStorage
import java.util.*

@Entity(tableName = "Transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: Long,
    val holderAccountId: Int,
    val consumptionAccountId: Int,
    val sum: Double,
    val comment: String?
) {
    fun toTransactionStorageModel() =
        TransactionModelForStorage(
            id = id,
            date = date,
            holderAccountId = holderAccountId,
            consumptionAccountId = consumptionAccountId,
            sum = sum,
            comment = comment

        )

}