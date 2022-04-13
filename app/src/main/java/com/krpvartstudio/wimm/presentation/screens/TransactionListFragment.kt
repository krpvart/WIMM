package com.krpvartstudio.wimm.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.krpvartstudio.wimm.R
import com.krpvartstudio.wimm.presentation.screens.adapters.TransactionListRecycleViewAdapter
import com.krpvartstudio.wimm.presentation.viewmodels.TransactionListViewModel
import kotlinx.android.synthetic.main.fragment_transaction_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionListFragment: Fragment(R.layout.fragment_transaction_list) {

    private val transactionListViewModel by viewModel<TransactionListViewModel>()
    private lateinit var transactionListAdapter: TransactionListRecycleViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionListAdapter = TransactionListRecycleViewAdapter()
        with(transaction_list_rv){
            adapter = transactionListAdapter
        }
        transactionListViewModel.transactionList.observe(viewLifecycleOwner){
            transactionListAdapter.transactionList = it
        }
    }
}