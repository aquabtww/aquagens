package ru.aquabtw.aquagens.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import ru.aquabtw.aquagens.generators.PlayerGenerator
import java.util.UUID

@Serializable
data class PlayerModel(
    @Contextual
    val uuid: UUID,
    val gens: MutableList<PlayerGenerator> = mutableListOf()
) {

    @Transient
    val taskIDs: MutableMap<Location, Int> = mutableMapOf()

    @Transient
    val player: Player? = Bukkit.getPlayer(uuid)

}
