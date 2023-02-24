package com.samfonsec.hscards.presentation.di

import com.samfonsec.hscards.presentation.viewModel.CardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CardsViewModel(get(), get()) }
}