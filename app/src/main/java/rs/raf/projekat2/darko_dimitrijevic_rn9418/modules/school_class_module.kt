package rs.raf.projekat2.darko_dimitrijevic_rn9418.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.schoolclass.SchoolClassDatabase
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.remote.SchoolClassService
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.schoolclass.SchoolClassRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.schoolclass.SchoolClassRepositoryImpl
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.SchoolClassViewModel

val schoolClassModule = module {

    viewModel { SchoolClassViewModel(schoolClassRepository = get()) }

    single<SchoolClassRepository> { SchoolClassRepositoryImpl(remoteDataSource = get(), localDataSource = get()) }

    single<SchoolClassService> { create(retrofit = get()) }

    single { get<SchoolClassDatabase>().getSchoolClassDao() }
}