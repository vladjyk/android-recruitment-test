package dog.snow.androidrecruittest

import android.app.Application
import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.data.db.ApplicationDatabase
import dog.snow.androidrecruittest.data.network.service.JsonPlaceholderApiService
import dog.snow.androidrecruittest.ui.main.child.vm.PhotoDetailActivityVMF
import dog.snow.androidrecruittest.ui.main.fragments.list.vm.PhotosListFragmentVMF
import dog.snow.androidrecruittest.util.DataCachingHelper
import dog.snow.androidrecruittest.util.NetworkUtil
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { NetworkUtil(this@App) }

        bind() from singleton { JsonPlaceholderApiService(this@App) }
        bind() from singleton { ApplicationDatabase(this@App) }
        bind() from singleton { Repository(instance(), instance())}
        bind() from singleton { DataCachingHelper(instance(), instance())}

        bind() from provider { PhotosListFragmentVMF(instance()) }
        bind() from provider { PhotoDetailActivityVMF(instance())}
    }


}