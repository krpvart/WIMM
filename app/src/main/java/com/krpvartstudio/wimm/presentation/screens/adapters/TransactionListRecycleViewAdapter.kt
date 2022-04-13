package com.krpvartstudio.wimm.presentation.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krpvartstudio.wimm.databinding.CardviewTransactionBinding
import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.model.TransactionModel
import com.krpvartstudio.wimm.presentation.ListDiffCallback

class TransactionListRecycleViewAdapter :
    RecyclerView.Adapter<TransactionListRecycleViewAdapter.TransactionViewHolder>() {

    var transactionList = listOf<TransactionModel>()
        set(value) {
            val callback = ListDiffCallback<TransactionModel>(transactionList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TransactionViewHolder(
            CardviewTransactionBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bindView(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class TransactionViewHolder(private val cardviewTransactionBinding: CardviewTransactionBinding) :
        RecyclerView.ViewHolder(cardviewTransactionBinding.root) {
        fun bindView(transaction: TransactionModel) {
            cardviewTransactionBinding.holderAccountName.text =
                transaction.holderAccountId.toString()
            cardviewTransactionBinding.consumptionAccountName.text =
                transaction.consumptionAccountId.toString()
            cardviewTransactionBinding.sumTransaction.text = transaction.sum.toString()
        }
    }

}