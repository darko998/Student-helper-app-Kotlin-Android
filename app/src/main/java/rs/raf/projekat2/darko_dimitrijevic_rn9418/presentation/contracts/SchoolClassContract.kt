package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.schoolclass.SchoolClassState

interface SchoolClassContract {

    interface ViewModel {

        val schoolClassesState : LiveData<SchoolClassState>

        fun fetchAll()
        fun getAll()
        fun getByName(name: String)
        fun getFiltered(name: String, professor: String, group: String, day: String)
    }
}