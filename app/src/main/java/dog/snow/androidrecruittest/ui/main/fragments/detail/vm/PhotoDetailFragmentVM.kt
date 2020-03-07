package dog.snow.androidrecruittest.ui.main.fragments.detail.vm

import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.Repository

class PhotoDetailFragmentVM(private val repository: Repository): ViewModel() {
    fun getPhotoDetail(photoId: Int) = repository.getPhotoDetail(photoId)
}