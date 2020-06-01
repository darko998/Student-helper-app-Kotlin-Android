package rs.raf.projekat2.darko_dimitrijevic_rn9418.application

import android.app.Application
import timber.log.Timber

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        init()
    }

    fun init() {
        initTimber()
    }

    fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}