package com.krpvartstudio.wimm.di

import com.krpvartstudio.wimm.domain.usecases.accountUseCases.AddAccountUseCase
import com.krpvartstudio.wimm.domain.usecases.accountUseCases.EditAccountUseCase
import com.krpvartstudio.wimm.domain.usecases.accountUseCases.GetListAccountUseCase
import com.krpvartstudio.wimm.domain.usecases.accountUseCases.GetAccountUseCase
import com.krpvartstudio.wimm.domain.usecases.transactionUseCases.AddTransactionUseCase
import com.krpvartstudio.wimm.domain.usecases.transactionUseCases.GetListTransactionUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<AddAccountUseCase> {
        AddAccountUseCase(get())
    }

    factory<GetListAccountUseCase>{
        GetListAccountUseCase(get())
    }

    factory<EditAccountUseCase> {
        EditAccountUseCase(get())
    }

    factory<GetAccountUseCase> {
        GetAccountUseCase(get())
    }

    factory<AddTransactionUseCase> {
        AddTransactionUseCase(get())
    }

    factory<GetListTransactionUseCase>{
        GetListTransactionUseCase(get())
    }

}
