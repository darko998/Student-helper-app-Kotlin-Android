package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.schoolclass.viewholder

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
    lateinit var day: TextView
    lateinit var time: TextView

    init {
        initView()
    }

    fun initView() {
        schoolClassName = containerView.findViewById(R.id.tv_note_title)
        schoolClassType = containerView.findViewById(R.id.tv_school_class_type)
        professor = containerView.findViewById(R.id.tv_professor)
        classroom = containerView.findViewById(R.id.tv_classroom)
        groups = containerView.findViewById(R.id.tv_groups)
        day = containerView.findViewById(R.id.tv_day)
        time = containerView.findViewById(R.id.tv_time)
    }

    fun bind(schoolClass: SchoolClass) {
        schoolClassName.text = schoolClass.name
        schoolClassType.text = schoolClass.type
        professor.text = schoolClass.professor
        classroom.text = schoolClass.classroom
        groups.text = schoolClass.groups
        day.text = schoolClass.day
        time.text = schoolClass.term
    }

}