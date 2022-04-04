package com.example.carapp.di

import com.example.carapp.domain.repository.CarFieldRepository
import com.example.carapp.domain.repository.CarFieldRepositoryImpl
import com.example.carapp.presentation.viewmodel.CarFieldViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DI {
    fun getModule() = module {
        single<CarFieldRepository> { CarFieldRepositoryImpl() }

        viewModel { CarFieldViewModel(carFieldRepository = get()) }
    }
}
