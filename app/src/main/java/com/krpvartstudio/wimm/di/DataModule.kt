package com.krpvartstudio.wimm.di

import com.krpvartstudio.wimm.data.RepositoryDataStorageImpl
import com.krpvartstudio.wimm.data.storage.local.LocalStorage
import com.krpvartstudio.wimm.data.storage.local.room.RoomStorage
import com.krpvartstudio.wimm.domain.repository.RepositoryAccount
import com.krpvartstudio.wimm.domain.repository.RepositoryTransaction
import org.koin.dsl.module


val dataModule = module {

    single<LocalStorage> {
        RoomStorage()
    }

    single<RepositoryAccount> {
        RepositoryDataStorageImpl(localStorage = get())
    }

    single<RepositoryTransaction>{
        RepositoryDataStorageImpl(localStorage = get())
    }

}

