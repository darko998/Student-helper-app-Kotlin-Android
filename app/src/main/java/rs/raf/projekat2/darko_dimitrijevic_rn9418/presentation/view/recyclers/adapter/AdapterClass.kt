package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.diff.DiffItemCallbackClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.viewholder.ViewHolderClass


class AdapterClass (diffItemCallback: DiffItemCallbackClass) : ListAdapter<SchoolClass, ViewHolderClass>(diffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.school_class_list_item, parent, false)

        return ViewHolderClass(containerView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val schoolClass = getItem(position)
        holder.bind(schoolClass)
    }
}