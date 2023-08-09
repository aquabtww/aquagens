package ru.aquabtw.aquagens.generators

import kotlinx.serialization.Serializable
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import ru.aquabtw.aquagens.miniMessage
import ru.aquabtw.aquagens.utils.setPD

@Serializable
data class Generator(
    val id: String,
    val material: Material,
    val name: String,
    val drop: Drop
) {
    fun toItemStack(): ItemStack {
        val item = ItemStack(material)
        item.editMeta {
            it.displayName(miniMessage.deserialize(name))
        }
        item.setPD(PersistentDataType.STRING, "gen", id)
        return item
    }

    @Serializable
    data class Drop(
        val material: Material,
        val name: String,
        val price: Int
    ) {
        fun toItemStack(): ItemStack {
            val item = ItemStack(material)
            item.editMeta {
                it.displayName(miniMessage.deserialize(name))
            }
            return item
        }
    }
}
