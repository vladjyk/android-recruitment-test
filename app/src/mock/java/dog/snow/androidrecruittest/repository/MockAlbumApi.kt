package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.data.db.entityes.Album
import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import dog.snow.androidrecruittest.repository.services.AlbumService


class MockAlbumApi(
    private val mockModelLoader: MockModelLoader
) : AlbumService {
    fun loadAlbums(): List<Album> {
        return mockModelLoader.loadAlbums(Array<Album>::class.java)
    }

    fun loadAlbum(albumId: Int): Album? {
        return mockModelLoader.loadAlbums(Array<Album>::class.java).find { it.id == albumId }
    }
}