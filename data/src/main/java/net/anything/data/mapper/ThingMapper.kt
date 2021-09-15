package net.anything.data.mapper

import net.anything.data.entities.StoredSign
import net.anything.data.entities.StoredThingEntity
import net.anything.domain.entity.ShowSign
import net.anything.domain.entity.ShowThingEntity

class ThingMapper {

    fun toStoredThing(show: ShowThingEntity): StoredThingEntity {
        show.apply {
            return StoredThingEntity(
                id,
                sign1?.toStoredSign(),
                sign2?.toStoredSign(),
                sign3?.toStoredSign()
            )
        }
    }

    fun toShowThing(stored: StoredThingEntity): ShowThingEntity {
        stored.apply {
            return ShowThingEntity(
                id,
                sign1?.toShowSign(),
                sign2?.toShowSign(),
                sign3?.toShowSign()
            )
        }
    }

    private fun ShowSign.toStoredSign(): StoredSign {
        return StoredSign(header, value)
    }

    private fun StoredSign.toShowSign(): ShowSign {
        return ShowSign(header, value)
    }
}