package kynv1.fsoft.basic.model

import kynv1.fsoft.basic.utils.Logger
import java.util.UUID


data class Counter(
    val id: String = UUID.randomUUID().toString(),
    val value: Int,
    val dateInMillis: Long
) {
    override fun equals(other: Any?): Boolean {
        return other is Counter && other.id == id
    }

    fun isTheSameContent(other: Any?): Boolean {
        Logger.lod("${other is Counter && other.id == id && other.value ==value}")
        return other is Counter && other.id == id && other.value ==value
    }
}

internal val currentTime by lazy { System.currentTimeMillis() }
internal const val ONE_DAY_MILLS = 86_400_000
