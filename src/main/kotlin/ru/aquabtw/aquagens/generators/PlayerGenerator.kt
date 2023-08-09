package ru.aquabtw.aquagens.generators

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bukkit.Location

@Serializable
data class PlayerGenerator(
    @Contextual
    val generator: Generator,
    @Contextual
    val location: Location
) {
    fun drop() {
        location.world.dropItemNaturally(location.clone().add(0.0, 1.0, 0.0), generator.drop.toItemStack())
    }
}
