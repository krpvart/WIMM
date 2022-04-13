package com.krpvartstudio.wimm.presentation.screens.adapters

import android.accounts.Account
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.krpvartstudio.wimm.databinding.CardviewAccountBinding
import com.krpvartstudio.wimm.domain.model.AccountModel
import java.text.FieldPosition

class AcoountListSpinnerAdapter(private val accountList: List<AccountModel>): BaseAdapter() {
    override fun getCount(): Int {
        return accountList.size
    }

    override fun getItem(position: Int): AccountModel {
        return accountList[position]
    }

    override fun getItemId(position: Int): Long {
        return accountList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val binding: CardviewAccountBinding =
           convertView?.tag as CardviewAccountBinding? ?:
           createBinding(parent.context)
       val account: AccountModel = getItem(position)
        binding.accountItemNameTextView.text = account.name
        binding.accountItemRemainingFundsTextView.text = account.remainingFunds.toString()
       return binding.root

    }

    private fun createBinding(context: Context): CardviewAccountBinding{
        val binding: CardviewAccountBinding = CardviewAccountBinding.inflate(LayoutInflater.from(context))
        binding.root.tag = binding
        return binding
    }

}