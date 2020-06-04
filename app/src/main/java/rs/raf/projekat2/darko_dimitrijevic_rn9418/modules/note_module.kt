package rs.raf.projekat2.darko_dimitrijevic_rn9418.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.note.NoteDatabase
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.note.NoteRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.note.NoteRepositoryImpl
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.NoteViewModel

val noteModule = module {

    viewModel { NoteViewModel( noteRepository = get()) }

    single<NoteRepository> { NoteRepositoryImpl(dataSource = get()) }

    single { get<NoteDatabase>().getDao() }
}