package ru.aquabtw.aquagens.utils

import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import ru.aquabtw.aquagens.AquaGens

private val plugin = AquaGens.get()

fun <T, Z : Any> ItemStack.setPD(type: PersistentDataType<T, Z>, key: String, value: Z) {
    editMeta {
        it.persistentDataContainer.set(NamespacedKey(plugin, key), type, value)
    }
}

fun <T, Z> ItemStack.getPD(type: PersistentDataType<T, Z>, key: String): Z? {
    return itemMeta.persistentDataContainer.get(NamespacedKey(plugin, key), type)
}

fun ItemStack.hasPD(key: String): Boolean {
    return itemMeta.persistentDataContainer.has(NamespacedKey(plugin, key))
}
