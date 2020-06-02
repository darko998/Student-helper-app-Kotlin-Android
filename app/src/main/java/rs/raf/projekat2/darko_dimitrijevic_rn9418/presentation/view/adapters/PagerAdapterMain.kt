package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.fragments.NotesFragment
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.fragments.ScheduleFragment
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.fragments.StatisticFragment


class PagerAdapterMain (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val ITEM_COUNT = 3
        const val SCHEDULE_FRAGMENT = 0
        const val NOTES_FRAGMENT = 1
        const val STATISTIC_FRAGMENT = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            SCHEDULE_FRAGMENT -> ScheduleFragment()
            NOTES_FRAGMENT -> NotesFragment()
            STATISTIC_FRAGMENT -> StatisticFragment()
            else -> ScheduleFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }


}