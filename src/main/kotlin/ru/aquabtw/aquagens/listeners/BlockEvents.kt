package ru.aquabtw.aquagens.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.persistence.PersistentDataType
import org.jetbrains.kotlin.library.SkippedDeclaration.id
import ru.aquabtw.aquagens.AquaGens
import ru.aquabtw.aquagens.miniMessage
import ru.aquabtw.aquagens.utils.getPD
import ru.aquabtw.aquagens.utils.hasPD

class BlockEvents(
    main: AquaGens
) : Listener {

    private val generatorManager = main.generatorManager
    private val playerManager = main.playerManager

    @EventHandler
    fun BlockPlaceEvent.onPlace() {
        if (!itemInHand.hasPD("gen")) return

        val generator = generatorManager.getGenerator(itemInHand.getPD(PersistentDataType.STRING, "gen"))
            ?: return

        val playerModel = playerManager.getPlayer(player.uniqueId)
        if (playerModel == null) {
            isCancelled = true
            return
        }

        playerManager.addGenerator(playerModel, block.location, generator)
        player.sendMessage(miniMessage.deserialize("You've placed a ${generator.id} generator."))
    }

    @EventHandler
    fun BlockDamageEvent.onBlockDamage() {
        val playerModel = playerManager.getPlayer(player.uniqueId)
        if (playerModel == null) {
            isCancelled = true
            return
        }

        val playerGenerator = playerModel.gens.firstOrNull { it.location == block.location }
            ?: return

        block.type = Material.AIR
        player.inventory.addItem(playerGenerator.generator.toItemStack())
        player.sendMessage(miniMessage.deserialize("You've picked up a ${playerGenerator.generator.id} generator."))
        playerManager.removeGenerator(playerModel, playerGenerator)
    }

    @EventHandler
    fun BlockBreakEvent.onBlockBreak() {
        val playerModel = playerManager.getPlayer(player.uniqueId)
        if (playerModel == null) {
            isCancelled = true
            return
        }

        val playerGenerator = playerModel.gens.firstOrNull { it.location == block.location }
            ?: return

        isDropItems = false
        player.inventory.addItem(playerGenerator.generator.toItemStack())
        player.sendMessage(miniMessage.deserialize("You've picked up a ${playerGenerator.generator.id} generator."))
        playerManager.removeGenerator(playerModel, playerGenerator)
    }

}