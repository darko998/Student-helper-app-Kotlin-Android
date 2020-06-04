package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.schoolclass.SchoolClassRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.SchoolClassContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.filter.FilterSchoolClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.schoolclass.SchoolClassState
import timber.log.Timber

class SchoolClassViewModel(private val schoolClassRepository: SchoolClassRepository) : ViewModel(), SchoolClassContract.ViewModel {

    override val schoolClassesState: MutableLiveData<SchoolClassState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    private val publishSubject : PublishSubject<FilterSchoolClass> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .switchMap {
                schoolClassRepository
                    .getFiltered(it.name, it.professor, it.group, it.day)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in school class publish subject.")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    when(it) {
                        is Resource.Success -> {
                            schoolClassesState.value = SchoolClassState.Success(it.data)
                        }
                        is Resource.Error -> {
                            schoolClassesState.value = SchoolClassState.Error("Error occurred while filtering data.")
                        }
                    }
                },
                {
                    schoolClassesState.value = SchoolClassState.Error("Error occurred while filtering data.")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

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

    override fun getAll() {
        val subscription = schoolClassRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Success -> schoolClassesState.value = SchoolClassState.Success(it.data)
                        is Resource.Error -> schoolClassesState.value = SchoolClassState.Error("Error occurred while fetching data from database.")
                        is Resource.Loading -> schoolClassesState.value = SchoolClassState.Loading
                    }
                },
                {
                    schoolClassesState.value = SchoolClassState.Error("Error occurred while fetching data from database.")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun getByName(name: String) {
        val subscription = schoolClassRepository
            .getByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Success -> schoolClassesState.value = SchoolClassState.Success(it.data)
                        is Resource.Error -> schoolClassesState.value = SchoolClassState.Error("Error occurred while fetching data with specific name.")
                        is Resource.Loading -> schoolClassesState.value = SchoolClassState.Loading
                    }
                },
                {
                    schoolClassesState.value = SchoolClassState.Error("Error occurred while fetching data with specific name.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getFiltered(name: String, professor: String, group: String, day: String) {
//        val subscription = schoolClassRepository
//            .getFiltered(name, professor, group, day)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    when(it) {
//                        is Resource.Success -> schoolClassesState.value = SchoolClassState.Success(it.data)
//                        is Resource.Error -> schoolClassesState.value = SchoolClassState.Error("Error occurred while fetching filtered data.")
//                        is Resource.Loading -> schoolClassesState.value = SchoolClassState.Loading
//                    }
//                },
//                {
//                    schoolClassesState.value = SchoolClassState.Error("Error occurred while fetching filtered data.")
//                    Timber.e(it)
//                }
//            )

        publishSubject.onNext(FilterSchoolClass(name, professor, group, day))
    }


    override fun onCleared() {
        super.onCleared()

        subscriptions.dispose()
    }

}