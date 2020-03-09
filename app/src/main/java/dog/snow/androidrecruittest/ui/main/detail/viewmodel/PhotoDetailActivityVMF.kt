package dog.snow.androidrecruittest.ui.main.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.data.Repository

class PhotoDetailActivityVMF(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoDetailActivityVM(
            repository
        ) as T
    }
}