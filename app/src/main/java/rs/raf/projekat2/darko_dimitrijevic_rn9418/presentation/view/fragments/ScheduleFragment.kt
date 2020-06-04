package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.SchoolClassContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.schoolclass.adapter.AdapterClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.recyclers.schoolclass.diff.DiffItemCallbackClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.schoolclass.SchoolClassState
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.SchoolClassViewModel
import timber.log.Timber
import java.util.*

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    lateinit var recyclerView : RecyclerView
    lateinit var adapter: AdapterClass

    private val schoolClassViewModel : SchoolClassContract.ViewModel by sharedViewModel<SchoolClassViewModel>()

    lateinit var spinnerGroup: Spinner
    lateinit var spinnerDay: Spinner
    lateinit var findBtn: Button
    lateinit var searchEditText: EditText
    lateinit var progresBar: ProgressBar

    var currTime: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        if(view != null)
            init(view)

        return view
    }

    fun init(view: View) {
        initUi(view)
        initSpinners(view)
        initRecycler(view)
        initObservers()
        initListeners(view)
    }

    fun initUi(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_schedule)
        spinnerGroup = view.findViewById(R.id.spinner_group)
        spinnerDay = view.findViewById(R.id.spinner_day)
        findBtn = view.findViewById(R.id.btn_find)
        searchEditText = view.findViewById(R.id.et_search)
        progresBar = view.findViewById(R.id.loadingPb)
    }

    fun initRecycler(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = AdapterClass(DiffItemCallbackClass())
        recyclerView.adapter = adapter
    }

    fun initObservers() {

        /** Here we listen on state changes while we fetching data from RAF API. */
        schoolClassViewModel.schoolClassesState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })

        /** Here we initiate fetching data from db. */
        schoolClassViewModel.getAll()


        /** Here we initiate fetching data from RAF API. */
        schoolClassViewModel.fetchAll()
    }

    private fun renderState(state: SchoolClassState) {
        when(state) {
            is SchoolClassState.Success -> {
                showLoadingState(false)

                adapter.submitList(state.schoolClasses)


                /** Here we just check if passed 2 seconds after last Toast message, because we
                 * don't want to send toast about successful fetched data more than once in 2 seconds. */
                if(Date().time - currTime > 2000) {

                    /** If fetched data is empty, notify user that there is no data. */
                    if (state.schoolClasses.isEmpty())
                        Toast.makeText(context, "No data with that filter.", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(context, "Data successfully fetched.", Toast.LENGTH_SHORT).show()

                    currTime = Date().time
                }

            }
            is SchoolClassState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, "Error occurred. You can see cached data. " + state.error, Toast.LENGTH_SHORT).show()
            }
            is SchoolClassState.Loading -> {
                showLoadingState(true)
                //Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            }
            is SchoolClassState.DataFetched -> {
                showLoadingState(false)
                // Toast.makeText(context, "Fresh data fetched from the server.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun initSpinners(view: View) {

        val groupsList = listOf("Group", "101", "102", "103", "104", "105", "106", "107", "108", "201", "202", "203", "204", "205", "206", "207", "208",
            "301", "302", "303", "304", "305", "306", "307", "308", "401", "402", "403", "404", "405", "406", "407", "408")

        val daysList = listOf("Day", "PON", "UTO", "SRE", "ČET", "PET", "SUB", "NED")

        val adapterGroup = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, groupsList) }
        val adapterDay = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, daysList) }

        adapterGroup?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterDay?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerGroup.adapter = adapterGroup
        spinnerDay.adapter = adapterDay


        /** Group spinner listener */
        spinnerGroup.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // either one will work as well
                // val item = parent.getItemAtPosition(position) as String
                val item = adapterGroup?.getItem(position)
            }
        }


        /** Day spinner listener */
        spinnerDay.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
        }
    }

    private fun initListeners(view: View) {

        findBtn.setOnClickListener {

            /** Initial setup for group and day. If user didn't selected none of these spinners, it
             * will be empty string on their places so we can query db. */
            var group = ""
            var day = ""

            val nameOrProfessor = searchEditText.text.toString()
            group = spinnerGroup.selectedItem.toString()
            day = spinnerDay.selectedItem.toString()

            /** 'Group' and 'Day' are just placeholder for spinners, and if someone of them is selected
             * we need to ignore that. */
            group = if (group != "Group") group else ""
            day = if (day != "Day") day else ""

            schoolClassViewModel.getFiltered(nameOrProfessor, nameOrProfessor, group, day)
        }
    }


    private fun showLoadingState(loading: Boolean) {
        recyclerView.isVisible = !loading
        spinnerGroup.isVisible = !loading
        spinnerDay.isVisible = !loading
        findBtn.isVisible = !loading
        searchEditText.isVisible = !loading
        progresBar.isVisible = loading
    }

}