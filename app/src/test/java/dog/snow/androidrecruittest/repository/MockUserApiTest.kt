package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.data.db.entityes.User
import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class MockUserApiTest {

    private lateinit var mockUserApi: MockUserApi

    @Before
    fun setUp(){
        val loader = MockModelLoader()
        mockUserApi = MockUserApi(loader)
    }

    @Test
    fun loadUsers() {
        val users = mockUserApi.loadUsers()
        val expected = User(1, "Leanne Graham", "Bret", "Sincere@april.biz", "1-770-736-8031 x56442")
        assertEquals(expected, users.first())
    }

    @Test
    fun loadUser() {
        val user = mockUserApi.loadUser(1)
        val expected = User(1, "Leanne Graham", "Bret", "Sincere@april.biz", "1-770-736-8031 x56442")
        assertEquals(expected, user)
    }
}