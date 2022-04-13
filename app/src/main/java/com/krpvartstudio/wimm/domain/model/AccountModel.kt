package com.krpvartstudio.wimm.domain.model

import com.krpvartstudio.wimm.domain.model.enums.AccountTypeEnum

//счет учета
data class AccountModel(
    val name: String, //Наименование
    val type: AccountTypeEnum,
    val remainingFunds: Double, //Остаток средств
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0

    }
}
