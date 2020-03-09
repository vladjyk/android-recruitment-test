package dog.snow.androidrecruittest.ui.main.child.viewmodel

import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.Repository

class PhotoDetailActivityVM(private val repository: Repository): ViewModel() {
    fun getPhotoDetail(photoId: Int) = repository.getPhotoDetail(photoId)
}