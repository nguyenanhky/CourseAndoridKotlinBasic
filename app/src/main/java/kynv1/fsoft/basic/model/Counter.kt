package kynv1.fsoft.basic.model

import java.util.UUID
import kotlin.time.ExperimentalTime


data class Counter(
    val id: String = UUID.randomUUID().toString(),
    val value: Int,
    val dateInMillis: Long
) {
    override fun equals(other: Any?): Boolean {
        return other is Counter && other.id == id
    }

    fun isTheSameContent(other: Any?): Boolean {
        return other is Counter && other.id == id && other.value == other.value
    }
}
// fetData
private val currentTime by lazy { System.currentTimeMillis() }
private const val ONE_DAY_MILLS = 86_400_000

@ExperimentalTime
fun generateFakeData() = (0..10).toList().map {
    Counter(value = it, dateInMillis = currentTime-it* ONE_DAY_MILLS)
}.toList()