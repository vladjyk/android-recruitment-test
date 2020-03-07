package dog.snow.androidrecruittest.ui.main.fragments.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.data.Repository

class PhotosListFragmentVMF(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosListFragmentVM(repository) as T
    }
}