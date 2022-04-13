package com.krpvartstudio.wimm.presentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krpvartstudio.wimm.R
import com.krpvartstudio.wimm.presentation.screens.adapters.AccountListRecycleViewAdapter
import com.krpvartstudio.wimm.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val  mainViewModel by viewModel<MainViewModel>()
    private lateinit var accountListRecycleViewAdapter: AccountListRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

  }


}