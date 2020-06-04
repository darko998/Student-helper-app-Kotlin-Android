package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.note.NoteRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.NoteContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note.*
import timber.log.Timber

class NoteViewModel (val noteRepository: NoteRepository) : ViewModel(), NoteContract.ViewModel {

    override val addDone: MutableLiveData<AddNoteState> = MutableLiveData()
    override val deleteDone: MutableLiveData<DeleteNoteState> = MutableLiveData()
    override val editDone: MutableLiveData<EditNoteState> = MutableLiveData()
    override val archiveDone: MutableLiveData<ArchiveNoteState> = MutableLiveData()

    override val notesState: MutableLiveData<NotesState> = MutableLiveData()

    val subscriptions = CompositeDisposable()

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
                        is Resource.Success -> notesState.value = NotesState.Success(it.data)
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
        val subscription = noteRepository
            .getByTitleOrContent(title, content, archived)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Success -> notesState.value = NotesState.SuccessFilteredData(it.data)
                        is Resource.Error -> notesState.value = NotesState.Error("Error occurred while fetching filtered data.")
                        is Resource.Loading -> notesState.value = NotesState.Loading
                    }
                },
                {
                    notesState.value = NotesState.Error("Error occurred while fetching filtered data.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(note: Note) {
        val subscription = noteRepository
            .delete(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    deleteDone.value = DeleteNoteState.Success
                },
                {
                    deleteDone.value = DeleteNoteState.Error("Error occurred while deleting note.")
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
                    archiveDone.value = ArchiveNoteState.Success
                },
                {
                    archiveDone.value = ArchiveNoteState.Error("Error occurred while archiving note.")
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()

        subscriptions.dispose()
    }

}