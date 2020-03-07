package dog.snow.androidrecruittest.ui.main.fragments.detail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.data.Repository

class PhotoDetailFragmentVMF(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoDetailFragmentVM(
            repository
        ) as T
    }
}