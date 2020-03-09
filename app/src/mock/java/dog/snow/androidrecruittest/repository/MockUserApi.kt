package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.data.db.entityes.User
import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import dog.snow.androidrecruittest.repository.services.UserService

class MockUserApi(
    private val mockModelLoader: MockModelLoader
) : UserService {
    fun loadUsers(): List<User> {
        return mockModelLoader.loadUsers(Array<User>::class.java)
    }

    fun loadUser(photoId: Int): User? {
        return mockModelLoader.loadUsers(Array<User>::class.java).find { it.id == photoId }
    }
}