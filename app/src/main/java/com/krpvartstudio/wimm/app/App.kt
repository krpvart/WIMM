package com.krpvartstudio.wimm.app

import android.app.Application
import android.content.Intent
import androidx.room.Room
import com.krpvartstudio.wimm.data.storage.local.room.RoomDB
import com.krpvartstudio.wimm.di.presentationModule
import com.krpvartstudio.wimm.di.dataModule
import com.krpvartstudio.wimm.di.domainModule
import com.krpvartstudio.wimm.presentation.MainActivity
import org.koin.core.context.startKoin

class App: Application() {


    companion object{
        lateinit var db: RoomDB
    }

    override fun onCreate() {

        super.onCreate()

        startKoin {
            modules(
                listOf(dataModule, domainModule, presentationModule)
            )
        }

        //TODO Убрать к релизу
        db = Room.databaseBuilder(this, RoomDB::class.java, "WIMMappDB")
            .fallbackToDestructiveMigration()
            .build()
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
    }
}