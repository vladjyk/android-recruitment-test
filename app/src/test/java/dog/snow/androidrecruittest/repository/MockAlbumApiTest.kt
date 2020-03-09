package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.data.db.entityes.Album
import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MockAlbumApiTest {

    private lateinit var mockAlbumApi: MockAlbumApi

    @Before
    fun setUp(){
        val loader = MockModelLoader()
        mockAlbumApi = MockAlbumApi(loader)
    }


    @Test
    fun loadAlbums() {
        val albums = mockAlbumApi.loadAlbums()
        val expected = Album(1,1, "quidem molestiae enim")
        assertEquals(expected, albums.first())
    }

    @Test
    fun loadAlbum() {
        val album = mockAlbumApi.loadAlbum(1)
        val expected = Album(1,1, "quidem molestiae enim")
        assertEquals(expected, album)
    }
}