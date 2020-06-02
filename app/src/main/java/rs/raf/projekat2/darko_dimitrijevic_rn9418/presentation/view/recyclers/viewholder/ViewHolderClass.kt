package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass

class ViewHolderClass (private val containerView: View) : RecyclerView.ViewHolder(containerView) {

    lateinit var schoolClassName: TextView
    lateinit var schoolClassType: TextView
    lateinit var professor: TextView
    lateinit var classroom: TextView
    lateinit var groups: TextView

    init {
        initView()
    }

    fun initView() {
        schoolClassName = containerView.findViewById(R.id.tv_school_class)
        schoolClassType = containerView.findViewById(R.id.tv_school_class_type)
        professor = containerView.findViewById(R.id.tv_professor)
        classroom = containerView.findViewById(R.id.tv_classroom)
        groups = containerView.findViewById(R.id.tv_groups)
    }

    fun bind(schoolClass: SchoolClass) {
        schoolClassName.text = schoolClass.name
        schoolClassType.text = schoolClass.type
        professor.text = schoolClass.professor
        classroom.text = schoolClass.classroom
        groups.text = schoolClass.groups
    }

}