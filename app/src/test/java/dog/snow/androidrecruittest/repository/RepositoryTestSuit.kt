package dog.snow.androidrecruittest.repository

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(MockAlbumApiTest::class, MockUserApiTest::class, MockPhotoApiTest::class)
class RepositoryTestSuit