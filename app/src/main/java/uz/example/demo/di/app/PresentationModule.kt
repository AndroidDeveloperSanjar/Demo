package uz.example.demo.di.app

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import uz.example.demo.presentation.RootViewModel
import uz.example.demo.presentation.chooser.ChooserViewModel
import uz.example.demo.presentation.main.MainViewModel
import uz.example.demo.presentation.settings.SettingsViewModel
import uz.example.demo.presentation.sign.login.LoginViewModel

val PresentationModule = module {
    viewModelOf(::RootViewModel)
    viewModelOf(::ChooserViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SettingsViewModel)
}