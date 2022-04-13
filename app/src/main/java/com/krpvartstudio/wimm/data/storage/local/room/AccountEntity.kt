package com.krpvartstudio.wimm.data.storage.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.krpvartstudio.wimm.data.storage.models.AccountModelForStorage
import com.krpvartstudio.wimm.domain.model.enums.AccountTypeEnum

@Entity(tableName = "Account")
data class AccountEntity
    (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val type: String,
    val remainingFunds: Double
) {
    fun toAccountStogareModel() = AccountModelForStorage(
        id = id,
        name = name,
        type = AccountTypeEnum.valueOf(type),
        remainingFunds = remainingFunds
    )
}