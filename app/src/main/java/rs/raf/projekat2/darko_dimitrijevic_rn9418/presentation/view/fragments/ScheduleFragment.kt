package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.SchoolClassContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.adapter.AdapterClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.diff.DiffItemCallbackClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.SchoolClassViewModel
import timber.log.Timber

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    lateinit var recyclerView : RecyclerView
    lateinit var adapter: AdapterClass

    private val schoolClassViewModel : SchoolClassContract.ViewModel by sharedViewModel<SchoolClassViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        if(view != null)
            init(view)

        return view
    }

    fun init(view: View) {
        initRecycler(view)
        initObservers()
    }

    fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_schedule)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = AdapterClass(DiffItemCallbackClass())
        recyclerView.adapter = adapter
    }

    fun initObservers() {

        val class1 =
            SchoolClass(
                1,
                "RMA",
                "Predavanja",
                "Nemanja Zirojevic",
                "RAF 10",
                "303,304",
                "Monday",
                "13:30"
            )
        val class2 =
            SchoolClass(
                2,
                "Masinsko ucenje",
                "Vezbe",
                "Igor Ciganovic",
                "RAF 2",
                "304",
                "Monday",
                "13:30"
            )
        val class3 =
            SchoolClass(
                1,
                "RMA",
                "Predavanja",
                "Nemanja Zirojevic",
                "RAF 10",
                "303,304",
                "Monday",
                "13:30"
            )
        var class4 =
            SchoolClass(
                2,
                "Masinsko ucenje",
                "Vezbe",
                "Igor Ciganovic",
                "RAF 2",
                "304",
                "Monday",
                "13:30"
            )
        var class5 =
            SchoolClass(
                1,
                "RMA",
                "Predavanja",
                "Nemanja Zirojevic",
                "RAF 10",
                "303,304",
                "Monday",
                "13:30"
            )
        var class6 =
            SchoolClass(
                2,
                "Masinsko ucenje",
                "Vezbe",
                "Igor Ciganovic",
                "RAF 2",
                "304",
                "Monday",
                "13:30"
            )
        var class7 =
            SchoolClass(
                1,
                "RMA",
                "Predavanja",
                "Nemanja Zirojevic",
                "RAF 10",
                "303,304",
                "Monday",
                "13:30"
            )
        var class8 =
            SchoolClass(
                2,
                "Masinsko ucenje",
                "Vezbe",
                "Igor Ciganovic",
                "RAF 2",
                "304",
                "Monday",
                "13:30"
            )

        var list = mutableListOf(class1, class2, class3, class4, class5, class6, class7, class8)

        adapter.submitList(list)



        /** Here we listen on state changes while we fetching data from RAF API. */
        schoolClassViewModel.schoolClassesState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
        })


        /** Here we initiate fetching data from RAF API. */
        schoolClassViewModel.fetchAll()

    }

}