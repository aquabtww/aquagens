package ru.aquabtw.aquagens.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import ru.aquabtw.aquagens.AquaGens

class ConnectionEvents(
    main: AquaGens
) : Listener {

    private val playerManager = main.playerManager

    @EventHandler
    fun PlayerJoinEvent.onJoin() {
        playerManager.addPlayer(player.uniqueId)
    }

    @EventHandler
    fun PlayerQuitEvent.onQuit() {
        playerManager.removePlayer(player.uniqueId)
    }

}