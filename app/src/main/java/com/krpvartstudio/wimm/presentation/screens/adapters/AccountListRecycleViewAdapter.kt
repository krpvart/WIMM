package com.krpvartstudio.wimm.presentation.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krpvartstudio.wimm.databinding.CardviewAccountBinding
import com.krpvartstudio.wimm.databinding.CardviewAddButtonBinding
import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.presentation.ListDiffCallback


class AccountListRecycleViewAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Listeners
    var onAddAccountButtonClickClickListener: (() -> Unit)? = null
    var onLongClickAccountListener: ((AccountModel) -> Unit)? = null
    var accountList = listOf<AccountModel>()
        set(value) {
            val callback = ListDiffCallback<AccountModel>(accountList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_CELL) {
            AccountViewHolder(CardviewAccountBinding.inflate(layoutInflater, parent, false))
        } else
            AddButtonViewHolder(CardviewAddButtonBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != accountList.size) {
            val account = accountList[position]
            (holder as AccountViewHolder).bindView(
                account,
                onLongClickAccountListener
            )
        } else {
            (holder as AddButtonViewHolder).bindView(onAddAccountButtonClickClickListener)
        }
    }

    class AccountViewHolder(private val cardviewAccountBinding: CardviewAccountBinding) :
        RecyclerView.ViewHolder(cardviewAccountBinding.root) {
        fun bindView(
            account: AccountModel,
            onLongClickMoneyAccountListener: ((AccountModel) -> Unit)?
        ) {
            cardviewAccountBinding.accountItemNameTextView.text = account.name
            cardviewAccountBinding.accountItemRemainingFundsTextView.text =
                account.remainingFunds.toString()
            cardviewAccountBinding.accountNameItemFAB.setOnLongClickListener {
                onLongClickMoneyAccountListener?.invoke(account)
                true
            }
        }
    }

    class AddButtonViewHolder(private val addButtonViewHolderBinding: CardviewAddButtonBinding) :
        RecyclerView.ViewHolder(addButtonViewHolderBinding.root) {
        fun bindView(onAddButtonClickClickListener: (() -> Unit)?) {
            addButtonViewHolderBinding.addButton.setOnClickListener {
                onAddButtonClickClickListener?.invoke()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == accountList.size) {
            VIEW_TYPE_FOOTER
        } else {
            VIEW_TYPE_CELL
        }
    }

    override fun getItemCount(): Int {
        return accountList.size + 1
    }

    companion object {
        const val VIEW_TYPE_FOOTER = 1
        const val VIEW_TYPE_CELL = 0

    }
}