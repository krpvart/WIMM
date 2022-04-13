package com.krpvartstudio.wimm.data.storage.models

import com.krpvartstudio.wimm.domain.model.enums.AccountTypeEnum

data class AccountModelForStorage(
    val name: String,
    val type: AccountTypeEnum,
    val remainingFunds: Double,
    val id: Int = UNDEFINED_ID
) {

    companion object {
        const val UNDEFINED_ID = 0
    }
}