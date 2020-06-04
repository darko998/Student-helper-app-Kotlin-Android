package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.notes.diff.NoteDiffItemCallback
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.notes.viewholder.NoteViewHolder

class NoteAdapter (noteDiffItemCallback: NoteDiffItemCallback, val clicked: (Note, Int) -> Unit) : ListAdapter<Note, NoteViewHolder>(noteDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.note_list_item, parent, false)

        return NoteViewHolder(containerView) { position: Int, flag: Int ->
            val note = getItem(position)

            clicked.invoke(note, flag)
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

}