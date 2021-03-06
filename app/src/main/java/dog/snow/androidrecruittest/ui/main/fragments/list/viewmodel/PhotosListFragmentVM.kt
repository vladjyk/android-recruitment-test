package dog.snow.androidrecruittest.ui.main.fragments.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.ui.main.fragments.list.viewmodel.model.PhotoWithExtendedInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotosListFragmentVM(private val repository: Repository): ViewModel() {
    private val photosLive = MutableLiveData<List<PhotoWithExtendedInfo>>()

    fun getPhotosWithExtendedInfo(): LiveData<List<PhotoWithExtendedInfo>> {
        fetchAll()
        return photosLive
    }

    fun fetchAll(){
        GlobalScope.launch {
            val photos = repository.getAllPhotosWithExtendedInfo()
            photosLive.postValue(photos)
        }
    }

    fun fetchWithTitle(title: String){
        GlobalScope.launch {
            val photos = repository.getPhotosWithExtendedInfo(title)
            photosLive.postValue(photos)
        }
    }
}