package com.krpvartstudio.wimm.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.krpvartstudio.wimm.R
import com.krpvartstudio.wimm.domain.model.AccountModel
import com.krpvartstudio.wimm.domain.model.enums.AccountTypeEnum
import com.krpvartstudio.wimm.presentation.viewmodels.MoneyAccountViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.RuntimeException

class AccountFragment : Fragment(R.layout.fragment_account) {
    private val accountViewModel by viewModel<MoneyAccountViewModel>()
    private var screenMode: String = UNKNOWN_MODE
    private var accountId: Int = AccountModel.UNDEFINED_ID
    private var accountType = AccountTypeEnum.HOLDER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (screenMode) {
            ADD_MODE -> launchAddMode()
            EDIT_MODE -> launchEditMode()
        }
    }

    private fun parseParams() {
        val args = arguments
        val mode = args?.getString(SCREEN_MODE)
        if (mode != ADD_MODE && mode != EDIT_MODE) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        when (screenMode) {
            ADD_MODE -> {
                if (!args.containsKey(ACCOUNT_TYPE)) {
                    throw RuntimeException("Param ACCOUNT_TYPE of MoneyAccount is absent")
                }
                accountType = AccountTypeEnum.valueOf(args.getString(ACCOUNT_TYPE)!!)
            }
            EDIT_MODE -> {
                if (!args.containsKey(ID_MONEY_ACCOUNT)) {
                    throw RuntimeException("Param ID of ItemShop is absent")
                }
                accountId = args.getInt(ID_MONEY_ACCOUNT, AccountModel.UNDEFINED_ID)
            }
        }
    }

    private fun launchAddMode() {
        save_btn.setOnClickListener {
            accountViewModel.addMoneyAccount(
                inputName= account_name_tiet.text.toString(),
                type = accountType,
                input_remaining_funds = remaining_funds_tiet.text.toString()
            )
            findNavController().navigate(R.id.action_accountFragment_to_mainFragment)
        }
    }

    private fun launchEditMode() {
        accountViewModel.getMoneyAccount(accountId)
        accountViewModel.account.observe(viewLifecycleOwner) {
            account_name_tiet.setText(it.name)
            remaining_funds_tiet.setText(it.remainingFunds.toString())
            save_btn.setOnClickListener {
                accountViewModel.editMoneyAccount(
                    account_name_tiet.text?.toString(),
                    remaining_funds_tiet.text?.toString()
                )
                findNavController().navigate(R.id.action_accountFragment_to_mainFragment)
            }
        }
    }

    companion object {
        const val SCREEN_MODE = "extra_mode"
        const val ACCOUNT_TYPE = "extra_account_type"
        const val ID_MONEY_ACCOUNT = "extra_id_money_account"
        private const val UNKNOWN_MODE = ""
        private const val ADD_MODE = "add_mode"
        private const val EDIT_MODE = "edit_mode"


    }

}