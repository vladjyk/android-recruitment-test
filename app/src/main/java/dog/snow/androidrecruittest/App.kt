package dog.snow.androidrecruittest

import android.app.Application
import dog.snow.androidrecruittest.data.network.service.JsonPlaceholderApiService
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { JsonPlaceholderApiService(this@App) }
    }


}