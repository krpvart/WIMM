package com.krpvartstudio.wimm.domain.model

data class TransactionModel(
    val id: Int = UNDEFINED_ID,
    val date: Long,
    val holderAccountId: Int,
    val consumptionAccountId: Int,
    val sum: Double,
    val comment: String?
){
    companion object {
        const val UNDEFINED_ID = 0

    }
}






