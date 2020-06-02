package rs.raf.projekat2.darko_dimitrijevic_rn9418.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.sharedpreferences.LoggedUserData
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.loggeduser.LoggedUserRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.loggeduser.LoggedUserRepositoryImpl
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.LoggedUserViewModel

val userModule = module {

    viewModel { LoggedUserViewModel(loggedUserRepository = get()) }

    single<LoggedUserRepository> { LoggedUserRepositoryImpl(loggedUserDataSource = get()) }

    single { LoggedUserData(sharedPreferences = get()) }
}