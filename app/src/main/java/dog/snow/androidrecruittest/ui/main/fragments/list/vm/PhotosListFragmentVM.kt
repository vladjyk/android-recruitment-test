package dog.snow.androidrecruittest.ui.main.fragments.list.vm

import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.Repository

class PhotosListFragmentVM(private val repository: Repository): ViewModel() {
    fun getPhotosWithExtendedInfo() = repository.getAllPhotosWithExtendedInfo()
}