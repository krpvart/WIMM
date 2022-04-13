package com.krpvartstudio.wimm.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.krpvartstudio.wimm.R
import com.krpvartstudio.wimm.domain.model.enums.AccountTypeEnum
import com.krpvartstudio.wimm.presentation.screens.AccountFragment.Companion.ACCOUNT_TYPE
import com.krpvartstudio.wimm.presentation.screens.AccountFragment.Companion.SCREEN_MODE
import com.krpvartstudio.wimm.presentation.viewmodels.MainViewModel
import com.krpvartstudio.wimm.presentation.screens.adapters.AccountListRecycleViewAdapter
import com.krpvartstudio.wimm.presentation.screens.adapters.AcoountListSpinnerAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var holderAccountListRecycleViewAdapter: AccountListRecycleViewAdapter
    private lateinit var consumptionAccountListRecycleViewAdapter: AccountListRecycleViewAdapter
    private lateinit var holderAccountListSpinnerAdapter: AcoountListSpinnerAdapter
    lateinit var consumptionAccountListSpinnerAdapter: AcoountListSpinnerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holderAccountListRecycleViewAdapter = AccountListRecycleViewAdapter()
        consumptionAccountListRecycleViewAdapter = AccountListRecycleViewAdapter()
        setupList(
            holder_account_list,
            holderAccountListRecycleViewAdapter,
            AccountTypeEnum.HOLDER
        )
        setupList(
            consumption_account_list,
            consumptionAccountListRecycleViewAdapter,
            AccountTypeEnum.CONSUMPTION
        )
        add_tranaction_btn.setOnClickListener{
            val currentDate: Date = Date()
            val timeStamp: Long = currentDate.time
            mainViewModel.addTransaction(
                    date = timeStamp,
                    fromAccountId = holder_spinner.selectedItemId.toInt(),
                    toAccountId = consumption_spinner.selectedItemId.toInt(),
                    sum = input_sum.text.toString().toDouble(),
                    comment = null
            )
        }

        show_transaction_btn.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_transactionListFragment)
        }





    }

    override fun onResume() {
        super.onResume()
        mainViewModel.updateList()
    }

    private fun setupList(
        recyclerView: RecyclerView,
        listRecycleViewAdapter: AccountListRecycleViewAdapter,
        accountTypeEnum: AccountTypeEnum
    ) {
        with(recyclerView) {
            adapter = listRecycleViewAdapter
        }
        when (accountTypeEnum) {
            AccountTypeEnum.HOLDER -> mainViewModel.accountHolderList.observe(
                viewLifecycleOwner
            ) {
                listRecycleViewAdapter.accountList = it
                holderAccountListSpinnerAdapter =  AcoountListSpinnerAdapter(it)
                holder_spinner.adapter = holderAccountListSpinnerAdapter
            }
            AccountTypeEnum.CONSUMPTION -> mainViewModel.accountConsuptionList.observe(
                viewLifecycleOwner
            ) {
                listRecycleViewAdapter.accountList = it
                consumptionAccountListSpinnerAdapter = AcoountListSpinnerAdapter(it)
                consumption_spinner.adapter = consumptionAccountListSpinnerAdapter
            }
        }

        listRecycleViewAdapter.onAddAccountButtonClickClickListener = {
            findNavController().navigate(
                R.id.action_mainFragment_to_moneyAccountFragment,
                bundleOf(
                    SCREEN_MODE to ADD_MODE,
                    ACCOUNT_TYPE to accountTypeEnum.toString()
                )
            )
        }

        listRecycleViewAdapter.onLongClickAccountListener = {
            findNavController().navigate(
                R.id.action_mainFragment_to_moneyAccountFragment,
                bundleOf(
                    SCREEN_MODE to EDIT_MODE,
                    AccountFragment.ID_MONEY_ACCOUNT to it.id
                )
            )
        }

    }

    companion object {
        const val EDIT_MODE = "edit_mode"
        const val ADD_MODE = "add_mode"
    }


}