package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.note.NoteRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.NoteContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.filter.FilterNote
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel (val noteRepository: NoteRepository) : ViewModel(), NoteContract.ViewModel {

    override val addDone: MutableLiveData<AddNoteState> = MutableLiveData()
    override val notesState: MutableLiveData<NotesState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    private val publishSubject: PublishSubject<FilterNote> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .getByTitleOrContent(it.title, it.content, it.archived)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    when(it) {
                        is Resource.Success -> {
                            Timber.e("------- " + it.data)

                            notesState.value = NotesState.Success(it.data)
                        }
                        is Resource.Error -> notesState.value = NotesState.Error("Error occurred while filtering notes.")
                    }
                },
                {
                    notesState.value = NotesState.Error("Error occurred while filtering notes.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insert(note: Note) {
        val subscription = noteRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddNoteState.Success
                },
                {
                    addDone.value = AddNoteState.Error("Error while creating new note.")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAll(archived: Boolean) {
        val subscription = noteRepository
            .getAll(archived)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Success -> {
                            Timber.e("------- " + it.data)
                            notesState.value = NotesState.Success(it.data)
                        }
                        is Resource.Loading -> notesState.value = NotesState.Loading
                        is Resource.Error -> notesState.value = NotesState.Error("Error occurred while fetching notes from db.")
                    }
                },
                {
                    notesState.value = NotesState.Error("Error occurred while fetching notes from db.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getByTitleOrContent(title: String, content: String, archived: Boolean) {
        publishSubject.onNext(FilterNote(title, content, archived))
    }

    override fun delete(note: Note) {
        val subscription = noteRepository
            .delete(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.DeleteSuccess
                },
                {
                    notesState.value = NotesState.DeleteError
                }
            )
        subscriptions.add(subscription)
    }

    override fun changeArchiveState(note: Note) {
        val subscription = noteRepository
            .changeArchiveState(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if(note.archived)
                        /** If forwarded note is archived, we need to unarchive. If operation is
                         * successful we need to notify user about success operation. There are two
                         * types of states, archive and unarchive.
                         * */
                        notesState.value = NotesState.UnArchiveSuccess
                    else
                        notesState.value = NotesState.ArchiveSuccess
                },
                {
                    if(note.archived)
                        notesState.value = NotesState.UnArchiveError
                    else
                        notesState.value = NotesState.ArchiveError
                }
            )
        subscriptions.add(subscription)
    }

    override fun update(note: Note) {
        val subscription = noteRepository
            .update(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.UpdateSuccess
                },
                {
                    notesState.value = NotesState.UpdateError
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()

        subscriptions.dispose()
    }

}