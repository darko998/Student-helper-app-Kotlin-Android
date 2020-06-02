package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.schoolclass.SchoolClassRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.SchoolClassContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.state.SchoolClassState
import timber.log.Timber

class SchoolClassViewModel(private val schoolClassRepository: SchoolClassRepository) : ViewModel(), SchoolClassContract.ViewModel {

    override val schoolClassesState: MutableLiveData<SchoolClassState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchAll() {
        val subscription = schoolClassRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> schoolClassesState.value = SchoolClassState.Loading
                        is Resource.Success -> schoolClassesState.value = SchoolClassState.DataFetched
                        is Resource.Error -> schoolClassesState.value = SchoolClassState.Error("Error happened while fetching data from server.")
                    }
                },
                {
                    schoolClassesState.value = SchoolClassState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()

        subscriptions.dispose()
    }

}