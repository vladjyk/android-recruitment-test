package dog.snow.androidrecruittest.util

import dog.snow.androidrecruittest.extend.exception.SlowConnectivityException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout

suspend fun <T> withTimeout(timeMillis: Long, block: suspend CoroutineScope.() -> T, doBeforeThrowBlock: () -> Unit): T{
    try {
       return withTimeout(timeMillis, block)
    } catch (e: TimeoutCancellationException){
        doBeforeThrowBlock()
        throw SlowConnectivityException()
    }
}