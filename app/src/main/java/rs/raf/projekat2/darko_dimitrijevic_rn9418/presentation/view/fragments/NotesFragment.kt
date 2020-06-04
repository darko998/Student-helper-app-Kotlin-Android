package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.NoteContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities.CreateNoteActivity
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities.EditNoteActivity
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.notes.adapter.NoteAdapter
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.notes.diff.NoteDiffItemCallback
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note.NotesState
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.NoteViewModel
import timber.log.Timber

class NotesFragment : Fragment(R.layout.fragment_notes) {

    lateinit var noteRecyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter

    lateinit var btnAddNote: FloatingActionButton
    lateinit var searchBox: EditText
    lateinit var switchBtn : Switch

    private val noteViewModel : NoteContract.ViewModel by sharedViewModel<NoteViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        view?.let { init(it) }

        return view
    }

    fun init(view: View) {
        initUi(view)
        initRecycler()
        initListeners()
        initObservers()
    }

    fun initUi(view: View) {
        noteRecyclerView = view.findViewById(R.id.rv_notes)
        btnAddNote = view.findViewById(R.id.btn_create_note)
        searchBox = view.findViewById(R.id.et_search_notes)
        switchBtn = view.findViewById(R.id.btn_switch)
    }

    fun initRecycler() {
        noteRecyclerView.layoutManager = LinearLayoutManager(this.context)
        noteAdapter = NoteAdapter(NoteDiffItemCallback()) { note: Note, flag: Int ->


            if(flag == 0) {
                /** If flag is 0 that means icon DELETE is clicked. */

                noteViewModel.delete(note)
            } else if (flag == 1) {
                /** If flag is 1 that means icon EDIT is clicked. */

                val intent = Intent(this.context, EditNoteActivity::class.java)
                intent.putExtra(EditNoteActivity.NOTE_ID, note.id)
                intent.putExtra(EditNoteActivity.NOTE_TITLE, note.title)
                intent.putExtra(EditNoteActivity.NOTE_CONTENT, note.content)
                intent.putExtra(EditNoteActivity.NOTE_ARCHIVED, note.archived)
                startActivity(intent)

            } else if (flag == 2) {
                /** If flag is 2 that means icon ARCHIVE is clicked. */
                Timber.e(note.archived.toString())

                noteViewModel.changeArchiveState(note)
            }

        }
        noteRecyclerView.adapter = noteAdapter
    }

    fun initListeners() {

        /** Here we start activity for creating new note, when floating button is pressed. */
        btnAddNote.setOnClickListener {
            val intent = Intent(this.context, CreateNoteActivity::class.java)
            startActivity(intent)
        }

        /** Here we listen on search box, so we can filter notes by entered words. */
        searchBox.doAfterTextChanged {
            val filter = it.toString()
            val archived = switchBtn.isChecked
            noteViewModel.getByTitleOrContent(filter, filter, archived)
        }


        switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            var filter = searchBox.text.toString()

            if(isChecked) {
                noteViewModel.getByTitleOrContent(filter, filter, true)
            } else {
                noteViewModel.getByTitleOrContent(filter, filter, false)
            }
        }
    }

    fun initObservers() {

        /** Here we listen on notes changes. */
        noteViewModel.notesState.observe(viewLifecycleOwner, Observer {
            Timber.e("darkoooooooooooooooooooooooooo " + it)
            renderState(it)
        })

        val archived = switchBtn.isChecked
        Timber.e(archived.toString())

        /** Here we initiate fetching data from db. */
        noteViewModel.getByTitleOrContent("", "", archived)
    }

    private fun renderState(state: NotesState) {
        when(state) {
            is NotesState.Success -> {
                noteAdapter.submitList(state.notes)

                if(state.notes.isEmpty())
                    Toast.makeText(context, "There is no notes in db.", Toast.LENGTH_SHORT).show()
//                else
//                    Toast.makeText(context, "Successfully fetched notes.", Toast.LENGTH_SHORT).show()

            }
            is NotesState.SuccessFilteredData -> {
                noteAdapter.submitList(state.notes)

                if(state.notes.isEmpty())
                    Toast.makeText(context, "There is no notes for that filter.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.Loading -> {
                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            }
            is NotesState.Error -> {
                Toast.makeText(context, "Error occured: " + state.error, Toast.LENGTH_SHORT).show()
            }
            is NotesState.UpdateSuccess -> {
                Toast.makeText(context, "Success update.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.UpdateError -> {
                Toast.makeText(context, "Error occurred while updating.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.DeleteSuccess -> {
                Toast.makeText(context, "Success delete.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.DeleteError -> {
                Toast.makeText(context, "Error occurred while deleting.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.ArchiveSuccess -> {
                Toast.makeText(context, "Success archive.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.ArchiveError -> {
                Toast.makeText(context, "Error occurred while archiving.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.UnArchiveSuccess -> {
                Toast.makeText(context, "Success unarchive.", Toast.LENGTH_SHORT).show()
            }
            is NotesState.UnArchiveError -> {
                Toast.makeText(context, "Error occurred while unarchive.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}