package com.example.gt_companion_app.di

import com.example.gt_companion_app.viewmodels.BoutDetailViewModel
import com.example.gt_companion_app.viewmodels.BoutListViewModel
import com.example.gt_companion_app.viewmodels.SetDetailSmallViewModel
import com.example.gt_companion_app.viewmodels.SetDetailViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel{ SetDetailViewModel( get() ) }
    viewModel { SetDetailSmallViewModel() }
    viewModel{ BoutDetailViewModel( get(), get(), get()) }
    viewModel { BoutListViewModel(get()) }
}