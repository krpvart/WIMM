package com.krpvartstudio.wimm.di

import com.krpvartstudio.wimm.presentation.viewmodels.MainViewModel
import com.krpvartstudio.wimm.presentation.viewmodels.MoneyAccountViewModel
import com.krpvartstudio.wimm.presentation.viewmodels.TransactionListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module{

    viewModel<MainViewModel> {
        MainViewModel(
            getListAccountUseCase = get(),
            addTransactionUseCase = get()
        )
    }

    viewModel<MoneyAccountViewModel>{
        MoneyAccountViewModel(
            addAccountUseCase = get(),
            editAccountUseCase = get(),
            getAccountUseCase = get()
        )
    }

    viewModel<TransactionListViewModel>{
        TransactionListViewModel(
            getListTransactionUseCase = get()
        )
    }

}