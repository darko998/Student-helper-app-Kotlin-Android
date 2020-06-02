package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.state.SchoolClassState

interface SchoolClassContract {

    interface ViewModel {

        val schoolClassesState : LiveData<SchoolClassState>
        fun fetchAll()
    }
}