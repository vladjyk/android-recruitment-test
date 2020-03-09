package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.data.db.entityes.Photo
import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import dog.snow.androidrecruittest.repository.services.PhotoService

class MockPhotoApi(
    private val mockModelLoader: MockModelLoader
) : PhotoService {
   fun loadPhotos(): List<Photo> {
        return mockModelLoader.loadPhotos(Array<Photo>::class.java)
    }

    fun loadPhoto(photoId: Int): Photo? {
        return mockModelLoader.loadPhotos(Array<Photo>::class.java).find { it.id == photoId }
    }
}