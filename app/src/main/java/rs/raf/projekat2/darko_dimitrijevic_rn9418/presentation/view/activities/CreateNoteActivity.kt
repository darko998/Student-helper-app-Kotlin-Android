package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_create_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.NoteContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note.AddNoteState
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.NoteViewModel
import java.util.*

class CreateNoteActivity : AppCompatActivity(R.layout.activity_create_note) {

    val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    fun init() {
        initListeners()
        initObservers()
    }

    fun initListeners() {
        btn_create_note_success.setOnClickListener {
            val title = et_note_title.text.toString()
            val content = et_note_content.text.toString()

            noteViewModel.insert(Note(0, title, content, false, Date().time))


            et_note_title.setText("")
            et_note_content.setText("")
        }

        btn_create_note_close.setOnClickListener {
            finish()
        }
    }

    fun initObservers() {
        noteViewModel.addDone.observe(this, Observer {
            renderState(it)
        })
    }


    fun renderState(state: AddNoteState) {
        when(state) {
            is AddNoteState.Success -> Toast.makeText(this, "Successfully inserted note.", Toast.LENGTH_SHORT).show()
            is AddNoteState.Error -> Toast.makeText(this, "Error occurred while inserting note in database.", Toast.LENGTH_SHORT).show()
        }
    }
}