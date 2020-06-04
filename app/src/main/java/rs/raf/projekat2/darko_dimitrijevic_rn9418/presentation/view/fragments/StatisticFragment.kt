package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.NoteContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.custom.VerticalBars
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note.FetchedNotesCountListState
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.NoteViewModel
import timber.log.Timber

class StatisticFragment : Fragment(R.layout.fragment_statistic) {

    lateinit var graph : VerticalBars

    private val noteViewModel : NoteContract.ViewModel by sharedViewModel<NoteViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        if (view != null) {
            init(view)
        }

        return view
    }

    private fun initUi(view: View) {
        graph = view.findViewById(R.id.statistic_graph)
    }

    fun init(view: View) {
        initUi(view)
        initObservers()
    }

    private fun initObservers() {
        noteViewModel.fetchedNotesCountListState.observe(viewLifecycleOwner, Observer {

            when(it) {
                is FetchedNotesCountListState.Success -> {

                    initGraph(it.data)
                }
                is FetchedNotesCountListState.Error -> {
                    Toast.makeText(this.context, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
        noteViewModel.getNotesCountForLast5Days();
    }

    fun initGraph(data : List<Int>) {
        graph.offsetsList = makeOffsetsList(data)
        graph.invalidate()
    }

    private fun makeOffsetsList(lists: List<Int>) : MutableList<Double>{

        val max = lists.max()
        val offsetsList = mutableListOf(1.0, 1.0, 1.0, 1.0, 1.0)

        if(max == 0) {
            return offsetsList
        }

        var k = 0
        for (list in lists) {
            if(max != null) {
                val offset = 1 - ((list * 1.0) / (max * 1.0))
                offsetsList[k] = offset
            }
            k++
        }

        return offsetsList
    }
}