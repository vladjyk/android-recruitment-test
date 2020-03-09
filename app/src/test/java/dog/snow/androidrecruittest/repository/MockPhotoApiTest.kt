package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.data.db.entityes.Photo
import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MockPhotoApiTest {

    private lateinit var mockPhotoApi: MockPhotoApi

    @Before
    fun setUp(){
        val loader = MockModelLoader()
        mockPhotoApi = MockPhotoApi(loader)
    }

    @Test
    fun loadPhotos() {
        val photos = mockPhotoApi.loadPhotos()
        val expected = Photo(1, 1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952")
        assertEquals(expected, photos.first())
    }

    @Test
    fun loadPhoto() {
        val photo = mockPhotoApi.loadPhoto(1)
        val expected = Photo(1, 1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952")
        assertEquals(expected, photo)
    }
}