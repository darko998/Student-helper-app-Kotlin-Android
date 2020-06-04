package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.notes.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note

class NoteDiffItemCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title && oldItem.content == newItem.content
    }

}