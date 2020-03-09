package dog.snow.androidrecruittest.util

import kotlinx.coroutines.delay

private const val TIME_WAITING_BEFORE_TRY_AGAIN = 100L

suspend fun <T> retryIO(block: suspend () -> T): T {
    while (true) {
        try {
            return block()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        delay(TIME_WAITING_BEFORE_TRY_AGAIN)
    }
}