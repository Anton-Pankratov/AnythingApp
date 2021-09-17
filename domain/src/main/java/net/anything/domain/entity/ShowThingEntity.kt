package net.anything.domain.entity

import java.io.Serializable

data class ShowThingEntity(
    val id: Int? = null,
    val sign1: ShowSign? = null,
    val sign2: ShowSign? = null,
    val sign3: ShowSign? = null
) : Serializable {

    fun getSigns() =
        listOf(sign1, sign2, sign3)
}