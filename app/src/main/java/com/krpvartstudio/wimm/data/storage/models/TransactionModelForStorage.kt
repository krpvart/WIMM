package com.krpvartstudio.wimm.data.storage.models

import java.util.*

data class TransactionModelForStorage(
    val id: Int,
    val date: Long,
    val holderAccountId: Int,
    val consumptionAccountId: Int,
    val sum: Double,
    val comment: String?
)





