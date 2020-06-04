package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.NoteContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.NoteViewModel

class EditNoteActivity : AppCompatActivity(R.layout.activity_edit_note) {

    lateinit var note: Note

    private val noteViewModel : NoteContract.ViewModel by viewModel<NoteViewModel>()

    companion object {
        const val NOTE_ID = "note_id"
        const val NOTE_TITLE = "note_title"
        const val NOTE_CONTENT = "note_content"
        const val NOTE_ARCHIVED = "note_archived"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    fun init() {
        initUi()
        initListeners()
    }

    fun initUi() {
        val intent = intent
        val id = intent.getLongExtra(NOTE_ID, -1)
        val title = intent.getStringExtra(NOTE_TITLE)
        val content = intent.getStringExtra(NOTE_CONTENT)
        val archived = intent.getBooleanExtra(NOTE_ARCHIVED, false)

        /** Save note we get from notesFragment in object note. */
        note = Note(id, title, content, archived)

        /** Initializing edit texts on view with data we get from intent. */
        et_note_title_edit.setText(title.toString())
        et_note_content_edit.setText(content.toString())
    }

    fun initListeners() {

        /** Listener to SAVE BUTTON */
        btn_edit_note_success.setOnClickListener {
            val title = et_note_title_edit.text.toString()
            val content = et_note_content_edit.text.toString()

            val tmpNote = Note(note.id, title, content, note.archived)
            noteViewModel.update(tmpNote)
            finish()
        }


        /** Listener to CLOSE BUTTON */
        btn_edit_note_close.setOnClickListener {
            finish()
        }
    }

}