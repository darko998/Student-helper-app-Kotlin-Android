package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.adapters.PagerAdapterMain

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    fun init(){
        initViewPager()
        initBottomNav()
    }

    fun initViewPager () {
        main_view_pager.adapter = PagerAdapterMain(supportFragmentManager)
    }

    fun initBottomNav () {

        nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_schedule -> {
                    main_view_pager.setCurrentItem(PagerAdapterMain.SCHEDULE_FRAGMENT, false)
                }
                R.id.nav_notes -> {
                    main_view_pager.setCurrentItem(PagerAdapterMain.NOTES_FRAGMENT, false)
                }
                R.id.nav_statistic -> {
                    main_view_pager.setCurrentItem(PagerAdapterMain.STATISTIC_FRAGMENT, false)
                }
                else -> {
                    main_view_pager.setCurrentItem(PagerAdapterMain.SCHEDULE_FRAGMENT, false)
                }
            }

            true
        }

    }
}