package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.notes.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_list_item.view.*
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note

class NoteViewHolder (private val containerView: View, val clicked : (Int, Int) -> Unit) : RecyclerView.ViewHolder(containerView) {

    lateinit var title: TextView
    lateinit var content: TextView
    lateinit var btnDelete: ImageView
    lateinit var btnEdit: ImageView
    lateinit var btnArchive: ImageView
    lateinit var btnUnArchive: ImageView

    init {
        initView()
        initListeners()
    }

    fun initView() {
        title = containerView.findViewById(R.id.tv_note_title)
        content = containerView.findViewById(R.id.tv_note_content)
        btnDelete = containerView.findViewById(R.id.icon_delete_note)
        btnEdit = containerView.findViewById(R.id.icon_edit_note)
        btnArchive = containerView.findViewById(R.id.icon_archive_note)
        btnUnArchive = containerView.findViewById(R.id.icon_unarchive_note)

    }

    fun initListeners() {

        /**
         *  0 is flag for delete icon
         *  1 is flag for edit icon
         *  2 is flag for archive icon
         * */

        btnDelete.setOnClickListener {
            clicked.invoke(adapterPosition, 0)
        }

        btnEdit.setOnClickListener {
            clicked.invoke(adapterPosition, 1)
        }

        btnArchive.setOnClickListener {
            clicked.invoke(adapterPosition, 2)
        }

        btnUnArchive.setOnClickListener {
            clicked.invoke(adapterPosition, 2)
        }
    }

    fun bind(note: Note) {
        title.text = note.title
        content.text = note.content

        if(note.archived) {
            btnArchive.isVisible = false
            btnUnArchive.isVisible = true
        } else {
            btnArchive.isVisible = true
            btnUnArchive.isVisible = false
        }
    }
}