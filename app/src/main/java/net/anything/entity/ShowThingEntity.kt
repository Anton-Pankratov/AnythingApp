package net.anything.entity

data class ShowThingEntity(
    val id: Int,
    val sign1: ShowSign? = null,
    val sign2: ShowSign? = null,
    val sign3: ShowSign? = null
) {

    fun getSigns() =
        listOf(sign1, sign2, sign3)
}