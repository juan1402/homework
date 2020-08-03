package com.sweatworks.homework.di

import com.sweatworks.homework.ui.detail.DetailViewModel
import com.sweatworks.homework.ui.home.HomeViewModel
import com.sweatworks.homework.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
}