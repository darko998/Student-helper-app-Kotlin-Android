package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.state

import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass

sealed class SchoolClassState {
    object Loading : SchoolClassState()
    object DataFetched : SchoolClassState()
    data class Success(val schoolClasses: List<SchoolClass>) : SchoolClassState()
    data class Error(val error: String) : SchoolClassState()
}