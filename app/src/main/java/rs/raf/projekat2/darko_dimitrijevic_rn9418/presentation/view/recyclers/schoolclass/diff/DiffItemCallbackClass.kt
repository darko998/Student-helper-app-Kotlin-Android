package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.schoolclass.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass

class DiffItemCallbackClass : DiffUtil.ItemCallback<SchoolClass>() {

    override fun areItemsTheSame(oldItem: SchoolClass, newItem: SchoolClass): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SchoolClass, newItem: SchoolClass): Boolean {
        return oldItem.type == newItem.type &&
                oldItem.professor == newItem.professor &&
                oldItem.classroom == newItem.classroom &&
                oldItem.groups == newItem.groups &&
                oldItem.day == newItem.day &&
                oldItem.term == newItem.term
    }

}