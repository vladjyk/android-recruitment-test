package dog.snow.androidrecruittest.repository.loaders

import com.google.gson.Gson


class MockModelLoader {
    private val mockLoader: MockLoader = MockLoader.createResourceLoader()

    fun <T> loadPhotos(classObject: Class<Array<T>>): List<T> {
        val json = mockLoader.loadJson("mocks/photos.json")
        return Gson().fromJson(json,classObject).toList()
    }

    fun <T> loadAlbums(classObject: Class<Array<T>>): List<T> {
        val json = mockLoader.loadJson("mocks/albums.json")
        return Gson().fromJson(json,classObject).toList()
    }

    fun <T> loadUsers(classObject: Class<Array<T>>): List<T> {
        val json = mockLoader.loadJson("mocks/users.json")
        return Gson().fromJson(json,classObject).toList()
    }
}